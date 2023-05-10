package com.example.finall.service;

import com.example.finall.dao.*;
import com.example.finall.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;


//@Service("customerService")
@Service
public class  CustomerServiceImpl implements CustomerService {
    int orderIDs=10;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private GroomerMapper groomerMapper;
    @Autowired
    private PetMapper petMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ServiceMapper serviceMapper;
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CommentsMapper commentsMapper;

    int rr=0;

//    public CustomerServiceImpl(CustomerMapper customerMapper, ManagerMapper managerMapper, GroomerMapper groomerMapper) {
//        this.customerMapper = customerMapper;
//        this.managerMapper = managerMapper;
//        this.groomerMapper = groomerMapper;
//    }

    @Override
    public List<Pet> getPet(int owner_phone) {
        return petMapper.QueryPetByOwner(owner_phone);
    }

    @Override
    public List<OrderInfo> orderinfo(int phone) {
        return orderMapper.QueryOrderByPhone(phone);
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerMapper.UpdateCustomer(customer);
    }

    public String deleteCustomer(int phone) {
        Customer customer = customerMapper.queryCustomerByPhone(phone);
        customerMapper.deleteByPhone(phone);

        return customer.phone + "has been deleted";
    }

    public String addPet(Pet pet) {
        petMapper.addPet(pet);
        return "a new pet is added";
    }

    public String updatePet(Pet pet) {

        petMapper.updatePet(pet);
        return "successfully update pet";
    }

    public String deletePet(int pet_id) {
        petMapper.deleteByPetId(pet_id);
        return "successfully delete";
    }
    public List<Pet> viewPets(){
        List<Pet> list=petMapper.viewPets();
        return list;
    }
    public String updatePassword(int phone, String new_password) {
        customerMapper.UpdatePassword(phone, new_password);
        return "successfully update password";
    }


    public Groomer viewGroomers(int employee_id) {
        Groomer groomer = groomerMapper.queryGroomerById(employee_id);
        return groomer;
    }

    public List<com.example.finall.pojo.Service> viewAllService() {
        List<com.example.finall.pojo.Service> list = serviceMapper.QueryService();
        return list;
    }

    public String viewServiceDetails(String type) {
        String content = serviceMapper.QueryServiceByName(type).description;
        return content;
    }


    public String createOrder(int phone, String groomer_id, String pet_id, Date start_time, Date end_time, String notes, String service_type) {
//        服务类型：美容400，洗澡200，寄养300，洁齿100
//        宠物类型：短毛，长毛（在原基础上加50）
//        宠物大小：小，中（在原基础上加50），大（在原基础上加150）
//        groomer等级：1（不加），2（不加），3（不加），4（加50），5（加80）

        int times = 1000000;
        Map map=new HashMap();
        int r = (int) (Math.random() * times);
        map.put(r,r);
        this.rr= (int) map.get(r);
        OrderInfo order = createorder(phone, groomer_id, pet_id, String.valueOf(rr),start_time, end_time, "unfinished completed",notes, service_type);
        if(order==null) return "1";
        Pet pet = petMapper.queryPetById(Integer.parseInt(pet_id));
        Groomer groomer = groomerMapper.queryGroomerById(Integer.parseInt(groomer_id));
        int expense = calculateExpense(pet, groomer, service_type);
        return " "+expense;
        //int flag = orderMapper.addOrder(order);
        //if(flag>=1) return " "+expense;  //创建成功
        //else {return "1";}
    }
    //此方法计算订单所需费用
    public int calculateExpense(Pet pet, Groomer groomer, String service_type){
        int expense = 0;
        if(service_type.equals("hairdressing")) expense =400;
        else if (service_type.equals("bathing")) expense =200;
        else if (service_type.equals("fostering")) expense =300;
        else expense =100;
        if(pet.type.equals("long-haired")) expense +=50;
        if (pet.size==2) expense +=50;
        if (pet.size==3) expense +=100;
        if(groomer.getRank()==4) expense +=50;
        if(groomer.getRank()==5) expense +=100;

        return expense;
    }


    //此方法生成一个OrderInfo对象（未插入数据库中）
    public OrderInfo createorder(int phone, String groomer_id, String pet_id,String order_id,Date start_time, Date end_time, String status,String notes,String service_type){
        Groomer groomer = groomerMapper.queryGroomerById(Integer.parseInt(groomer_id));
        if(groomer.getIsFree() == null || groomer.getIsFree()==0) return null; //groomer放假
        List<OrderInfo> order_list = orderMapper.QueryOrderByEmployeeid(Integer.parseInt(order_id));
        for (OrderInfo order : order_list) {
            boolean a = order.end_time.after(start_time) && order.end_time.before(end_time);
            boolean b =  order.start_time.after(start_time) && order.start_time.before(start_time);
            //a和b来判断所选时间段groomer是否有空
            if(a||b) return null; //在此时间段groomer没空
        }
        OrderInfo s_order = new OrderInfo(phone,Integer.parseInt(pet_id),Integer.parseInt(order_id),start_time, end_time, status,Integer.parseInt(groomer_id), service_type,notes);
        return s_order;
    }

    public OrderInfo createorder(int phone, String groomer_id, String pet_id,Date start_time, Date end_time, String status,String notes,String service_type){
        Groomer groomer = groomerMapper.queryGroomerById(Integer.parseInt(groomer_id));
        if(groomer.getIsFree()==0) return null; //groomer放假
        List<OrderInfo> order_list = orderMapper.QueryOrderByEmployeeid(Integer.parseInt(groomer_id));
        for (OrderInfo order : order_list) {
            boolean a = order.end_time.after(start_time) && order.end_time.before(end_time);
            boolean b =  order.start_time.after(start_time) && order.start_time.before(start_time);
            //a和b来判断所选时间段groomer是否有空
            if(a||b) return null; //在此时间段groomer没空
        }
        OrderInfo s_order = new OrderInfo(phone, Integer.parseInt(pet_id),1,start_time, end_time, status,Integer.parseInt(groomer_id), service_type,notes);
        return s_order;
    }

    //客户更新预约信息(秦茂晖)

//    public String updateOrder(final String order_id, final String status, int phone, String groomer_id, String pet_id, Date start_time, Date end_time, String notes,String service_type){
//        OrderInfo order = createorder(phone, groomer_id, pet_id, order_id, start_time, end_time, status, notes, service_type);
//        if(order==null) return "The groomer is not available at that period, please choose another one.";
//        Pet pet = petMapper.QueryPetById(Integer.getInteger(pet_id));
//        Groomer groomer = groomerMapper.queryGroomerById(Integer.getInteger(groomer_id));
//        int expense = calculateExpense(pet, groomer, service_type);
//
//        int flag = orderMapper.updateOrder(order);
//        if(flag == 0) return "Your changes have been saved. The expense of the updated order is gonna be "+ expense;
//        else return "fail to save your changes";
//    }
    @Override
    public Map<Groomer, List<String>> viewAvilabletime() {
        List<Groomer> groomers = groomerMapper.listAllGroomer();
        Map<Groomer, List<String>> map = new HashMap<>();
        for (Groomer groomer : groomers) {
            List<OrderInfo> orderInfos = orderMapper.QueryOrderByEmployeeid(groomer.getEmployeeId());
            List<String> list = new ArrayList<>();
            Date curDate = new Date();
//            Date today = (Date) curDate.clone();
//            today.setHours(0);
//            today.setMinutes(0);
//            today.setSeconds(0);
//            //移除查询时已完成的订单
//            for (OrderInfo order : orderInfos){
//                if(order.end_time.before(curDate)) orderInfos.remove(order);
//            }
            //将未来的订单放入list中
            for (OrderInfo order : orderInfos){
                if(order.end_time.before(curDate)) break;
                String start = order.start_time.toString();
                String end = order.end_time.toString();
                list.add(start+" - "+end);
            }
            map.put(groomer, list);
        }
        return map;
    }


    public List<OrderInfo> viewAllOrders(int phone){
        List<OrderInfo> list=orderMapper.QueryOrderByPhone(phone);
        return list;
    }

    public OrderInfo viewOrderDetails(String order_id){
        OrderInfo order=orderMapper.QueryOrderById(order_id);
        int employee_id=order.employee_id;
        int pet_id=order.pet_id;
        String pet_type=petMapper.queryPetById(pet_id).type;
        String status=order.status;

        String service_type=order.service_type;

        return order;
    };

    public String deleteOrder(int phone,String order_id){
        List<OrderInfo> list=orderMapper.QueryOrderByPhone(phone);
        for(OrderInfo item : list){
            if(item.order_id==Integer.parseInt(order_id)){
                orderMapper.deleteOrderById(order_id);
                return "order has been deleted";
            }

            return "Your input is not correct";
        }
        return "Your input is not correct";

    }

    public List<OrderInfo> QueryOrderByPet(String pet_id){
//        //List list=orderMapper.QueryOrderByPet(pet_id);
//        List<OrderInfo> list=orderMapper.QueryOrderByPhone(customer.phone);
//        List<OrderInfo> list2;
//        for(OrderInfo item: list){
//            if(item.pet_id==Integer.getInteger(pet_id)){
//                list2=orderMapper.QueryOrderByPet(pet_id);
//                return list2;
//            }
//        }
//        return null;

        List<OrderInfo> list1=orderMapper.QueryOrderByPet(Integer.parseInt(pet_id));
        return list1;
    };

    public OrderInfo QueryOrderById(Customer customer,String order_id){
        List<OrderInfo> list=orderMapper.QueryOrderByPhone(customer.phone);
        OrderInfo orderInfo;
        for(OrderInfo item : list){
            if(item.order_id==Integer.getInteger(order_id)){
                orderInfo=orderMapper.QueryOrderById(order_id);
                return orderInfo;
            }


        }
        return null;
    }

    public List<OrderInfo> QueryOderByPhone(int phone){
        List list=orderMapper.QueryOrderByPhone(phone);
        return list;
    }

    public String createVip(int money,int phone){
        if(money>=1000){

            //Customer customer=customerMapper.QueryCustomerByPhone(phone);
            Card card= new Card(phone,money);
            cardMapper.addCard(card);
//            customer.card_id=customer.phone;
//            Card card=cardMapper.QueryCardById(customer.card_id);
//            card.balance=money;
//            customerMapper.UpdateCustomer(customer);
        }
        return "charge vip successfully";
    };

    public String chargeVip(int money,int phone){
        if(money>=0){

            //Customer customer=customerMapper.QueryCustomerByPhone(phone);
            //customer.card_id=customer.phone;
            Card card=cardMapper.QueryCardById(phone);
            card.balance=card.balance+money;
            Card card2=new Card(phone,card.balance);
            cardMapper.updateCard(card2);
            //customerMapper.UpdateCustomer(customer);
        }
        return "charge vip successfully";
    }

    public Card viewVip(int phone){
        //Customer customer=customerMapper.QueryCustomerByPhone(phone);
        Card card=cardMapper.QueryCardById(phone);
        return card;
    }
    //查看商品 by yin
    public List<Product> viewAllProduct(){
        List<Product> list = productMapper.queryProduct();
        return list;
    }

    //查看商品详情 by yin
    public Product viewProductDetails(String name){
        Product product=productMapper.QueryProductByName(name);
//        String product_name=product.getProductName();
//        int product_price=product.getPrice();
//        //path
//        String filePath=product.getFilename();
//        String description=product.getDescription();

        //Card QueryCardById(int card_id);

        return product;
    }
    //购买商品 by yin
    public String buyProduct(String name, int phone){
        //购买商品不打折，购买某种服务类型打折
        Customer customer=customerMapper.queryCustomerByPhone(phone);
        //customer.card_id=customer.phone;
        Card card=cardMapper.QueryCardById(customer.phone);

        Product product=productMapper.QueryProductByName(name);
        int price=product.getPrice();
        int balance=card.balance;

        if(balance>=price){
            //更新余额
            balance=card.balance-price;
            customerMapper.UpdateCustomer(customer);
            return "Buy the product successfully!";
        }
        else
            return "The balance is insufficient, please charge it!";


    }

    @Override
    public String commentShopAndGroomers(Comments comments) {
        return null;
    }

    @Override
    public String postComments(Comments comments) {
        return null;
    }
//    public String OrderByPhone(){
//        Manager managers=managerMapper.QueryManager();
//        String phone= managers.phone;
//        return phone;
//
//
//    }

    public Customer viewInformation(int phone){
        Customer customer=customerMapper.ViewInformation(phone);
        return customer;
    }

    public void updateCustomer(int phone,String username){
        customerMapper.UpdateCustomerInfo(phone,username);
    }


    //zhang rui ha
    public Product ViewProductDetails(String name){
        Product product=productMapper.QueryProductByName(name);
        return product;
    }

    public void addEvaluations(String order_id,int phones,String service_rating,String shop_rating,String groomer_rating,String otherComment){
        Comments comments=new Comments(otherComment,Integer.parseInt(order_id),Integer.parseInt(groomer_rating),Integer.parseInt(service_rating),Integer.parseInt(shop_rating));
        commentsMapper.addComments(comments);
    }

    public void completeEvaluationOrder(String order_id){
        orderMapper.updateStatus(Integer.parseInt(order_id),"evaluation completed");
    }

    public List<Comments> viewComments(){
        List<Comments> list=commentsMapper.QueryAllComments();
        return list;
    }

    public void addAppointment(int phone,String groomer_id, String pet_id,Date start_time, Date end_time,String note, String service_type){
        OrderInfo order = new OrderInfo(phone, Integer.parseInt(pet_id),rr,start_time, end_time, "unfinished completed",Integer.parseInt(groomer_id),service_type,note);
        orderMapper.addOrder(order);


    }


    public void deleteEvaluation(int order_id){
        commentsMapper.deleteCommentsById(order_id);
    }


}
