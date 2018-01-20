package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.json.*;


import javax.websocket.server.PathParam;
import java.util.*;

@Controller
@SessionAttributes({"currentClient", "currentOrder"})
public class HomeController {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;


    /*
     * Add current client in model attribute
     */
    @ModelAttribute("currentClient")
    public Client setUpClientForm() {
        return new Client("","","","",1);
    }

    @ModelAttribute("currentOrder")
    public Order setUpOrderForm() {
        Set<Product> massproduct2 = new HashSet<Product>();
        return new Order("empty",1 ,massproduct2);
    }

      @RequestMapping("/")                                                        // welcompage
      public String viewHome(Map<String, Object> model) {
          model.put("message", "hello");
          return "welcomePage";
        }   // end welcompage

    @RequestMapping("/myorders")                                                // myorderpage
    public String myorderpage(@ModelAttribute("currentClient") Client myClient,
                            @ModelAttribute("currentOrder") Order myOrder,
                            Model model3,
                            @RequestParam(value="idProduct", defaultValue="2") Integer idProduct,
                            @RequestParam(value="makeorder", defaultValue="2") String makeorder,
                            @PathParam("nameProduct") String nameProduct) {

        if (!myOrder.getOrdertext().equals("empty") ) {
        }

        List<Order> list2 = new ArrayList<Order>(myClient.getOrders());
        model3.addAttribute("orderslist", list2);



        return "myorders";
    } // end myorderpage



    @RequestMapping("/orderpage")                                                // orderpage
    public String orderpage(@ModelAttribute("currentClient") Client myClient,
                            @ModelAttribute("currentOrder") Order myOrder,
                            Model model,
                            @RequestParam(value="idProduct", defaultValue="2") Integer idProduct,
                            @RequestParam(value="makeorder", defaultValue="2") String makeorder,
                            @PathParam("nameProduct") String nameProduct) {




        if (!myOrder.getOrdertext().equals("empty") ) {
        }
        System.out.println("order - - "+myOrder.getOrdertext());
        System.out.println(myOrder.getProducts().size());
        //System.out.println(myOrder.getProducts().iterator().next().getProduct());
        List<Product> list1 = new ArrayList<Product>(myOrder.getProducts());
        System.out.println(list1.get(1).getProduct());
        model.addAttribute("orderlist", list1);

        if ( makeorder.equals("Заказать товар?") && (myClient.getStatus()==1)  ) {
            model.addAttribute("errorOrder", "Вам необходимо зарегистрироваться.");
            makeorder = "2";
            System.out.println("st   "+myClient.getStatus()+myClient.getUsername() );
            }
        if ( makeorder.equals("Заказать товар?") && (myClient.getStatus()==2) && !(myOrder.getOrdertext().equals("empty")) ) {

               Set<Order> massorder1 = new HashSet<Order>();

               //massorder1.addAll( myClient.getOrders());
               //System.out.println("111  " + myClient.getUsername()  );
               //massorder1.add(myClient.getOrders().iterator().next());
               System.out.println(myClient.getUsername());

            myOrder = orderRepository.save(myOrder);
            massorder1.add(myOrder);
            myClient.setOrders(massorder1);
            clientRepository.save(myClient);



            model.addAttribute("errorOrder", "Заказ сделан.");
             makeorder = "2";
        }
        return "order";
    } // end orderpage


    @RequestMapping(value= "/startpage1", method = RequestMethod.POST)               // startpage1
    @ResponseBody
    public String startpage1( @RequestParam(value="string1", defaultValue="2"  ) String string1
                           ) {
if(!string1.equals("2")) {
    System.out.println("string1:"+ string1);
};
        System.out.println("string1:"+ string1);
        //myClient.setLastname("Sokolov");
//Создание объекта json
        JSONObject obj=new JSONObject();
// Кодирование объекта json
        obj.put("name"," Иванов Михаил");
        obj.put("age",new Integer(21));
        //Декодирование объекта json
        System.out.println("Name:"+obj.get("name"));
        System.out.println("Age:"+obj.get("age"));
        //System.out.println("json1:"+json1.get("product"));

        return "startpage";
    } // end startpage1





    @RequestMapping(value= "/startpage", method = RequestMethod.GET)               // startpage
    public String startpage(@ModelAttribute("currentClient") Client myClient,
                            @ModelAttribute("currentOrder") Order myOrder,
                            Model model2,
                            //@RequestParam(value="json1") JSONObject json1,
                            @RequestParam(value="string12",  defaultValue="2") String string1,
                            @RequestParam(value="idProduct",  defaultValue="2") Integer idProduct,
                            @RequestParam(value="strProduct",  defaultValue="2") String strProduct,
                            @PathParam("nameProduct") String nameProduct) {

        //myClient.setLastname("Sokolov");
//Создание объекта json
        JSONObject obj=new JSONObject();
// Кодирование объекта json
        obj.put("name"," Иванов Михаил");
        obj.put("age",new Integer(21));
        //Декодирование объекта json
        System.out.println("Name:"+obj.get("name"));
        System.out.println("Age:"+obj.get("age"));
        //System.out.println("json1:"+json1.get("product"));


        model2.addAttribute("client2",myClient.getFirstname());
        model2.addAttribute("vol4",idProduct);
        model2.addAttribute("productlist", productRepository.findAll());
        model2.addAttribute("productsize", productRepository.count() );
        //model2.addAttribute("productname", productRepository.findOne(idProduct).getProduct() );
        //model2.addAttribute("productn", productRepository.findById(idProduct).getProduct() );
if (idProduct!=2) {
    System.out.println("order1 "+idProduct);
    Set<Product> massproduct = new HashSet<Product>();
    massproduct = myOrder.getProducts();
    massproduct.add(productRepository.findById(idProduct));
    myOrder.setProducts(massproduct);
    myOrder.setOrdertext("order1");
} else {
    System.out.println("order-2 "+strProduct );
    Set<Product> massproduct = new HashSet<Product>();
    massproduct.add(productRepository.findOne(1));
    massproduct.add(productRepository.findOne(2));
    myOrder.setProducts(massproduct);
    myOrder.setOrdertext("order2");
    //System.out.println(myOrder.getProducts().iterator().next().getProduct() );
    System.out.println(myOrder.getOrdertext() );
    }
        return "startpage";
    } // end startpage


    @RequestMapping(value= "/members", method = RequestMethod.GET)                      // login page
    public String members(@ModelAttribute("currentClient") Client myClient,
                          Map<String, Object> model,
			              @RequestParam(value="loginClient", defaultValue="2") String login1,
                          @RequestParam(value="passwordClient", defaultValue="2") String pass1) {


          model.put("errorLogin", "Введите логин и пароль.");
          if (!login1.equals("2")&&!pass1.equals("2")) {

              if (clientRepository.existsByUsername(login1) ) {
                  if (clientRepository.findFirstByUsername(login1).getPassword().equals(pass1)) {

                      myClient.setUsername(login1);
                      myClient.setPassword(pass1);
                      myClient.setStatus(2);
                      myClient.setFirstname(clientRepository.findFirstByUsername(login1).getFirstname());
                      myClient.setOrders(clientRepository.findFirstByUsername(login1).getOrders()  );
                      myClient.setId(clientRepository.findFirstByUsername(login1).getId()  );
                      return "redirect:/startpage";
                  }
                  else model.put("errorLogin", "Неверный пароль.");
               } else model.put("errorLogin", "Неверный ввод.");


              myClient.setLastname("Petrov");

            if(!productRepository.existsByProduct("product1")) productRepository.save(new Product("product1"));
            if(!productRepository.existsByProduct("product2")) productRepository.save(new Product("product2"));

            Set<Product> massproduct = new HashSet<Product>();
            massproduct.add(productRepository.findOne(1));
            massproduct.add(productRepository.findOne(2));
            Order order1 = new Order();
            order1.setOrdertext("order1");
            order1.setProducts(massproduct);
            orderRepository.save(order1);

            Set<Product> massproduct2 = new HashSet<Product>();
            massproduct2.add(productRepository.findOne(1));
            //massproduct2.add(productRepository.findOne(2));
            Order order2 = new Order();
            order2.setOrdertext("order2");
            order2.setProducts(massproduct2);
            orderRepository.save(order2);

            Set<Order> massorder = new HashSet<Order>();
            massorder.add(order1);
            massorder.add(order2);
            myClient.setOrders(massorder);
            if (!clientRepository.existsByUsername(login1)) clientRepository.save(myClient);
            }

            return "login";
    } // end login page




    @RequestMapping(value= "/register")                                                             // registration page
    public String indexMetod3(@ModelAttribute("currentClient") Client myClient,
                              Map<String, Object> model,
                              @RequestParam(value="user1", defaultValue="2") String user1,
                              @RequestParam(value="passwordClient", defaultValue="2") String passwordClient,
                              @RequestParam(value="first1", defaultValue="2") String first1,
                              @RequestParam(value="last1", defaultValue="2") String last1) {
        if (!user1.equals("2")&&!first1.equals("2") && !last1.equals("2") && !passwordClient.equals("2")) {
            myClient.setUsername(user1);
            myClient.setPassword(passwordClient);
            myClient.setFirstname(first1);
            myClient.setLastname(last1);
            myClient.setStatus(2);
            clientRepository.save(myClient);
            model.put("mess1", "Регистрация прошла успешно");
           } else {
            if (myClient.getStatus() == 2) {
                model.put("mess1", "Вы зарегистрированы.");
            }
            if (myClient.getStatus() == 1) {
                model.put("mess1", "Заполните регистрационную форму.");
            }
        }
        return "registration";
    } //end registration page


    @RequestMapping("/test/{name}")
    @ResponseBody
    public String testMetod(@PathVariable("name") String name, @PathParam("value[]") String[] values){
        return "Hello test "+name + " " + Arrays.toString(values);
    }
}
