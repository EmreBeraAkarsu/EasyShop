package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;


//Added annotation for using the rest controller
@RestController
//Added annotation above the class to have the common url outlined for the methods below
@RequestMapping("categories")
//Added to have the server handle requests from different origins
@CrossOrigin
public class CategoriesController
{
    //Both DAO s are used in this classes methods. We need to have a class wide variable to access those DAO s
    private CategoryDao categoryDao;
    private ProductDao productDao;


    // created an Autowired controller to inject the categoryDao and ProductDao

    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }


    //GetMapping annotation used to use this method for get all categories requests. URL is only the default path
    @GetMapping("")
    //Let all users without any need for login to access the method
    @PreAuthorize("permitAll()")
    public List<Category> getAll()
    {
        try {
            // find and return all categories
            return categoryDao.getAllCategories();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    //GetMapping annotation used to use this method for get a category with a specific id requests. URL is categories/{id}
    @GetMapping("/{id}")
    //Let all users without any need for login to access the method
    @PreAuthorize("permitAll()")
    //Path needs the id variable to provide a specific category. It is given as a PathVariable
    public Category getById(@PathVariable int id)
    {

        //Initiate the category as null
        Category category = null;

        try {
            //Call the Dao's method to get the category by id
            category = categoryDao.getById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }

        //Throw an exception if the category is null
        if (category == null){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        //Return the retrieved category
        return category;
    }

    //GetMapping annotation used to use this method for get a category with a specific id requests. URL is categories/{categoryid}/products
    @GetMapping("{categoryId}/products")
    //Let all users without any need for login to access the method
    @PreAuthorize("permitAll()")
    //Path needs the Categoryid variable to provide a specific category. It is given as a PathVariable
    public List<Product> getProductsById(@PathVariable int categoryId)
    {

        try {
            return productDao.listByCategoryId(categoryId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }



    //PostMapping annotation used to use this method for post a new category. URL is categories
    @PostMapping("")
    //This method is only authorized to admins with the below annotation
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    //The new category information will be passed as a request body. The return of this method is a category with the id
    public Category addCategory(@RequestBody Category category)
    {
        // insert the category by calling the create function of the Category DAO and return the category with the id
        return categoryDao.create(category);
    }


    //PutMapping annotation used to use this method for post a new category. URL is categories/{id}
    @PutMapping("/{id}")
    //This method is only authorized to admins with the below annotation
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    //The new category information as request body and id of the category wanted to be updated will be passed as a path variable.
    public void updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        // update the category by id by calling the method of DAO
        categoryDao.update(id, category);
    }


    //DeleteMapping annotation used to use this method for post a new category. URL is categories/{id}
    @DeleteMapping("/{id}")
    //This method is only authorized to admins with the below annotation
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    //Give 204 status response when it is successful
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //The id of the category wanted to be updated will be passed as a request body.
    public void deleteCategory(@PathVariable int id)
    {
        // delete the category by id by calling the DAO method
        categoryDao.delete(id);
    }
}
