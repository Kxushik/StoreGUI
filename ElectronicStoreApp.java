import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.input.*;

public class ElectronicStoreApp extends Application {
    ElectronicStore model;
    ElectronicStoreView view;

    public void start(Stage primaryStage) {
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView(model);

        //Item in stock is added to cart
        view.getAddtoCart().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleAddtoCart(); }
        });

        //Update when item selected in stock view
        view.getStockView().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {view.update("null");}
        });

        //Update when item is selected in cart view
        view.getCartItemsView().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {view.update("null"); }
        });

        //Item is removed from cart
        view.getRemovefromCart().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleremovefromCart();}
        });

        //Complete sale button
        view.getCompleteSale().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleCompleteSale();}
        });

        //Reset Store Button
        view.getResetStore().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleResetStore();}
        });

        //Creating the application window
        primaryStage.setTitle("Electronic Store Application - " + model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, 800, 400));
        primaryStage.show();
        view.update("stock");

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void handleAddtoCart() {
        view.update("first");

        //Checks which index is selected and sells
        Product addToCart = view.getStockView().getSelectionModel().getSelectedItem();
        //This method is to help with selecting the right index when items are removed from the list view

        //Sells the selected index
        addToCart.sellUnits(1);
        //Adds the sold object to a cartList
        for (int i = 0; i < view.getCartItems().length - 1; i++) {
            if (view.getCartItems()[i] == null) {
                view.getCartItems()[i] = addToCart;
                break;
            }
        }
        view.update("stock");
        view.update("cart");

    }

    public void handleremovefromCart() {
        view.update("first");
        //Makes sure the index that was selected was the right item the user selected
        Product removeFromCartIndex = view.getCartItemsView().getSelectionModel().getSelectedItem();

        //Updates the stock quantity
        removeFromCartIndex.setStockQuantityAdd(1);
        removeFromCartIndex.setSoldQuantitySub(1);
        for (int i=0; i<view.getCartItems().length;i++){
            if (view.getCartItems()[i]!=null){
                if (view.getCartItems()[i].toString().equals(removeFromCartIndex.toString())){
                    view.getCartItems()[i]=null;
                    break;
                }
            }
        }
        view.update("stock");
        view.update("cart");
    }

    public void handleCompleteSale() {
        view.setNumberSalesVal(1);

        //Finds total revenue and updates it, sets dollarperSale val, aswell as sends all the items from the cart array to the sold array
        for (int i = 0; i < view.getCartItems().length - 1; i++) {
            if (view.getCartItems()[i]!=null) {
                view.setRevenueVal(view.getCartItems()[i].getPrice());
            }
            for (int j = 0; j < view.getSoldStock().length - 1; j++) {
                if (view.getSoldStock()[j] == null) {
                    view.getSoldStock()[j] = view.getCartItems()[i];
                    view.getCartItems()[i]=null;
                }
            }
            view.setDollarPerSaleVal(view.getRevenueVal()/view.getNumberSalesVal());
        }

        //Resets cart total
        view.setCartTotal(0.0);
        view.update("cart");
        view.update("sale");
    }
    public void handleResetStore(){
        model = ElectronicStore.createStore();
        view.setModel(model);

    }
}

