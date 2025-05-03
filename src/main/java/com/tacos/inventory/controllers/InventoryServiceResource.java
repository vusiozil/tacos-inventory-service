package com.tacos.inventory.controllers;

import com.tacos.inventory.dto.DecreaseStockRequest;
import com.tacos.inventory.dto.IngredientStockRequest;
import com.tacos.inventory.model.IngredientStock;
import com.tacos.inventory.repository.IngredientRepository;
import com.tacos.inventory.repository.IngredientStockRepository;
import com.tacos.inventory.service.InventoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryServiceResource {

    @Inject
    private IngredientStockRepository stockRepo;

    @Inject
    private IngredientRepository ingredientRepository;

    @Inject
    private InventoryService inventoryService;

    @GET
    @Path("/{ingredientId}")
    public Response getStock(@PathParam("ingredientId") String ingredientId) {

        return stockRepo
                .getById(ingredientId)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.BAD_REQUEST).entity("IngredientStock not found for ID: " + ingredientId))
                .build();

    }

    @GET
    @Path("/")
    public List<IngredientStock> getStock() {
        return stockRepo.findAll();
    }

    @POST
    @Path("/")
    public Response addStock(IngredientStockRequest stockRequest) {

        return ingredientRepository
                .getById(stockRequest.getId())
                .map(ingredient -> {

                    IngredientStock stock = new IngredientStock();
                    stock.setIngredient(ingredient);
                    stock.setQuantity(stockRequest.getQuantity());
                    stock.setIngredientId(stockRequest.getId());
                    stockRepo.saveOrUpdate(stock);

                    return Response
                            .status(Response.Status.CREATED)
                            .entity("Stock added.")
                            .build();
                }).orElse(Response
                        .status(Response.Status.NOT_FOUND)
                        .entity("Cant add quantity of the ingredient that doesn't exists")
                        .build());
    }

    @PUT
    @Path("/decrease")
    public Response decreaseStock(DecreaseStockRequest request) {
        try {
            inventoryService.decreaseStock(request.getIngredientQuantities());
            return Response.ok("Stock decreased successfully.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Failed to decrease stock: " + e.getMessage())
                    .build();
        }
    }

}