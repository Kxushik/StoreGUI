//Necessary Imports {JavaFx and Util}
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.util.*;
import java.util.Arrays;
import java.util.HashSet;

public class ElectronicStoreView extends Pane{
    //Instantiating Variables & Privatizing for proper encapsulation
    private ElectronicStore model;
    private TextField numberSales, revenue, dollarPerSale;
    private int numberSalesVal;
    private double revenueVal,dollarPerSaleVal;
    private Button resetStore,addtoCart,removefromCart,completeSale;
    private ListView<Product> popularStockView, cartItemsView,stockView;
    private Product[] cartItems,soldStock;
    private double cartTotal;
    private Label cartTotalLabel;


    public ElectronicStoreView(ElectronicStore model){
        //Setting values to the variables
        numberSalesVal=0;
        revenueVal=0.0;
        dollarPerSaleVal=0.0;
        cartTotal =0.0;
        this.model=model;


        //Taking the stock, and finding the total amount of items in the stock
        int totalItems=0;
        for (int i =0; i<model.stock.length-2;i++){
            totalItems+=model.stock[i].getStockQuantity();
        }
        //Creating the new Product Arrays with respective values
        cartItems=new Product[totalItems+1];
        soldStock= new Product[totalItems+1];


        /* GUI Seperated into 4 Sections
        Section 1 - Store Summary
            Section 1 contains anything that is found under the Store Summary Visual of the Gui
            # Sales - TextField & Label
            Revenue - TextField & Label
            $/Sale: - TextField & Label

         Section 2 - Most Popular Items
            Includes anything found under the Most Popular Items Section
            Label - Most Popular Item
            ListView - Most Popular Items
            Button - Reset Store

         Section 3 - Store Stock
            Includes anything that is found under the Store Stock Section
            Label - Store Stock
            ListView - Store Stock
            Button - Add to Cart

         Section 4 - Cart Items
            Includes anything found under the Current Cart Section
            Label - Current Cart, Cart Total
            ListView - Current Cart
            Button - Remove from cart, complete sale
         */

        //Section 1
        Label labelStore = new Label("Store Summary");
        labelStore.relocate(30,5);
        labelStore.setPrefSize(100,10);
        getChildren().add(labelStore);

        Label labelSales = new Label("# Sales:");
        labelSales.relocate(10,25);
        labelSales.setPrefSize(100,10);
        getChildren().add(labelSales);

        numberSales = new TextField();
        numberSales.setText("0");
        numberSales.relocate(75,25);
        numberSales.setPrefSize(100,10);
        numberSales.setEditable(false);
        getChildren().add(numberSales);

        Label labelRevenue = new Label("Revenue");
        labelRevenue.relocate(10,60);
        labelRevenue.setPrefSize(100,10);
        getChildren().add(labelRevenue);

        revenue = new TextField();
        revenue.setText("0.00");
        revenue.relocate(75,60);
        revenue.setPrefSize(100,10);
        revenue.setEditable(false);
        getChildren().add(revenue);

        Label labeldollarPerSale = new Label("$ / Sale:");
        labeldollarPerSale.relocate(10,95);
        labeldollarPerSale.setPrefSize(100,10);
        getChildren().add(labeldollarPerSale);

        dollarPerSale = new TextField();
        dollarPerSale.setText("N/A");
        dollarPerSale.relocate(75,95);
        dollarPerSale.setPrefSize(100,10);
        dollarPerSale.setEditable(false);
        getChildren().add(dollarPerSale);

        //Section 2
        Label popularItems = new Label("Most Popular Items");
        popularItems.relocate(30,125);
        getChildren().add(popularItems);

        popularStockView = new ListView<>();
        popularStockView.relocate(10,145);
        popularStockView.setPrefSize(165,180);
        getChildren().add(popularStockView);
        popularStockView.getItems().add(model.stock[0]);
        popularStockView.getItems().add(model.stock[1]);
        popularStockView.getItems().add(model.stock[2]);

        resetStore = new Button("Reset Store");
        resetStore.relocate(45,350);
        getChildren().add(resetStore);

        //Section 3
        Label stockLabel = new Label("Store Stock");
        stockLabel.relocate(290,5);
        getChildren().add(stockLabel);

        stockView = new ListView<>();
        stockView.relocate(200,25);
        stockView.setPrefSize(250,300);
        getChildren().add(stockView);

        addtoCart = new Button("Add to Cart");
        addtoCart.relocate(280,350);
        getChildren().add(addtoCart);

        //Section 4
        Label cartLabel = new Label("Current Cart");
        cartLabel.relocate(590,5);
        getChildren().add(cartLabel);

        cartItemsView = new ListView<>();
        cartItemsView.relocate(500,25);
        cartItemsView.setPrefSize(250,300);
        getChildren().add(cartItemsView);

        cartTotalLabel = new Label("($" + String.format("%.2f",cartTotal) + ")");
        getCartLabel().relocate(658, 5);
        getChildren().add(cartTotalLabel);


        removefromCart = new Button("Remove from Cart");
        removefromCart.relocate(500,350);
        getChildren().add(removefromCart);

        completeSale = new Button("Complete Sale");
        completeSale.relocate(650, 350);
        getChildren().add(completeSale);


    }
    //get/set Methods
    public Label getCartLabel(){return cartTotalLabel;}

    public Button getAddtoCart() {return addtoCart;}
    public Button getRemovefromCart(){return removefromCart;}
    public Button getResetStore(){return resetStore;}
    public Button getCompleteSale() {return completeSale; }

    public ListView<Product> getStockView() {return stockView;}
    public ListView<Product> getCartItemsView() {return cartItemsView;}

    public Product[] getCartItems() {return cartItems;}
    public Product[] getSoldStock() {return soldStock;}

    public int getNumberSalesVal() {return numberSalesVal;}
    public void setNumberSalesVal(int amount){numberSalesVal+=amount;}

    public double getRevenueVal() {return revenueVal;}
    public void setRevenueVal(double revenueVal) {this.revenueVal += revenueVal; }

    public double getDollarPerSaleVal() {return dollarPerSaleVal;}
    public void setDollarPerSaleVal(double dollarPerSaleVal) {this.dollarPerSaleVal = dollarPerSaleVal;}

    public double getCartTotal() {return cartTotal;}
    public void setCartTotal(double cartTotal) {this.cartTotal += cartTotal;}

    /*Update method is separated into section
    When update() is called, it is only updating depending on the section specified

     */
    public void update(String needsUpdate){

        //Checks if item in stock is selected and enables button
        int selectionStock = stockView.getSelectionModel().getSelectedIndex();
            if (selectionStock==-1){addtoCart.setDisable(true);}
            else{addtoCart.setDisable(false);}


        //Checks if item in cart is selected and enables button
        int selectionCart = cartItemsView.getSelectionModel().getSelectedIndex();
            if (selectionCart==-1){removefromCart.setDisable(true);}
            else{removefromCart.setDisable(false);}

        //System.out.println(selectionCart+""+selectionStock);
        //Checks if there items in the cart and enables button
            boolean cartHasItems=false;
            for (int i=0; i<cartItems.length-1;i++){
                if (cartItems[i] != null) {
                    cartHasItems = true;
                    break;
                }
            }
            if (cartHasItems){completeSale.setDisable(false);}
            else{completeSale.setDisable(true);}

            //First Subpart of update() - Updates anything related to the stock
            if (needsUpdate.equals("stock")){
                //Clears Stock View of old items and re-adds items with stockQuantity >0
                stockView.getItems().clear();
                for (int i = 0; i < (model.stock).length ; i++) {
                    if (model.stock[i]!=null) {
                        if (model.stock[i].getStockQuantity() > 0) {
                            stockView.getItems().add(model.stock[i]);
                        }
                    }
                }
            }
            //Second Subpart - Handles anything related to the cart and its actions
            if (needsUpdate.equals("cart")) {
                cartHasItems=true;
                //Clears items in the cart & updates the view with what is in the cartItems list & sets cartTotal Label
                cartItemsView.getItems().clear();
                cartTotal=0.0;
                for (int i = 0; i < (cartItems.length ); i++) {
                    if (cartItems[i] != null) {
                        cartItemsView.getItems().add(cartItems[i]);
                        cartTotal+=cartItems[i].getPrice();
                    }
                }
                getCartLabel().setText("($"+String.format("%.2f",cartTotal)+")");
            }
            //Third Subpart - Handles anything needed to be done after complete sale button is pressed
            if (needsUpdate.equals("sale")){
                //Clears whats in the popular stock view
                popularStockView.getItems().clear();
                //Updates the text fields under store summary
                numberSales.setText(String.valueOf(numberSalesVal));
                revenue.setText(String.valueOf(String.format("%.2f",revenueVal)));
                if (revenueVal==0.0){dollarPerSale.setText("N/A");}
                else{dollarPerSale.setText(String.valueOf(String.format("%.2f",dollarPerSaleVal)));}


                //Convert soldStock (list) to soldStock(set) and then back t a list
                //This gets rid of duplicate items in the soldStock list (Makes it easier to find popular items)
                Set<Product> set = new HashSet<>(Arrays.asList(soldStock));
                List<Product> list = new ArrayList<>(set);

                //Sorts the soldStock from greatest to least
                Product[] highTemp = new Product[1];
                for (int i =0; i<list.size()-1;i++){
                    for (int j=0;j<list.size()-i-1;j++){
                        if (list.get(j)!=null && list.get(j+1)!=null) {
                            if (list.get(j+1).getSoldQuantity() > list.get(j).getSoldQuantity()&& !list.get(j).toString().contains(list.get(j+1).toString())) {
                                highTemp[0]=list.get(j);
                                list.set(j,list.get(j+1));
                                list.set(j+1,highTemp[0]);
                            }
                        }
                    }
                }

                //First three actual items of the sorted list, are now the three popular items
                int count=0;
                for (int i=0;i<list.size();i++){
                    if(list.get(i)!=null){
                        if (count<3) {
                            popularStockView.getItems().add(list.get(i));
                            count++;
                        }

                    }
                }

                //Im not sure if theres an issue with javafx, but sometimes the cartItems would not clear.
                //Adding this makes sure that the cart view is cleared (Sorta like a backup)
                for (int i=0;i<cartItems.length-1;i++){
                    if (cartItems[i]!=null){
                        cartItems[i]=null;
                    }
                }
                update("cart");
            }

    }

    //Puts in the new model, and resets all values assosciated to the old model. Aswell as updates the view
    //Just updating the model itself did not work. Had to change stuff around
    public void setModel(ElectronicStore model){
        this.model=model;
        numberSalesVal=0;
        revenueVal=0.0;
        dollarPerSaleVal=0.0;
        soldStock = new Product[model.stock.length];

        //Clears stuff in the popular items listview
        popularStockView.getItems().clear();
        update("stock");
        update("cart");
        update("sale");

    }


}
