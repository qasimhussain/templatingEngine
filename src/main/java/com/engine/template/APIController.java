package com.engine.template;

import Constants.ApplicatioConstants;
import com.engine.template.models.request.InvoicePayload;
import com.engine.template.models.request.Payload;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.*;

import static Constants.ApplicatioConstants.getTemplateIfExistent;

@Controller
public class APIController {

    private static final Logger logger = LoggerFactory.getLogger(APIController.class);

    @Autowired
    private SpringTemplateEngine templateEngine;


    @RequestMapping("/")
    public String index() {
        return "Hello Spring World!";
    }

    @PostMapping("/testHTML")
    public String testHTML(@RequestBody Payload payload, Model model) {


        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.getDefault());
        model.addAttribute("serverTime", payload.getEmailType());
        return "email1.html";
    }

    @PostMapping("/getTemplate/{templateID}")
    public String getTemplate(@RequestBody Map<String,Object> payload, @PathVariable(value="templateID") String tempId , Model model) {

        String tempName = getTemplateIfExistent("email" + tempId + ".html");
        if (tempName == null) return "no such template";

        for (String key : payload.keySet()){

            Object value = payload.get(key);

            if( value instanceof String){
                model.addAttribute(key, payload.get(key));

            }else if (value instanceof ArrayList<?>){

                for (int i=0; i < ((ArrayList) value).size() ; i++){
                    if(((ArrayList<?>)value).get(i) instanceof HashMap<?,?>){
                        model.addAttribute(key, value);
                    }
                }
            }
        }

        return tempName;
    }



    @PostMapping("/getInvoiceEmailTemplate")
    public String getInvoiceEmailTemplate(@RequestBody InvoicePayload payload,Model model) {

        logger.info(" env selected : " + System.getProperty("profileId") + " baseurl: " + ApplicatioConstants.BASE_URL);


        model.addAttribute("invoice_logo_image_url", ApplicatioConstants.BASE_URL + payload.getInvoiceLogoImageURL());
        model.addAttribute("invoice_paid_amount", payload.getInvoicePayedAmount());
        model.addAttribute("invoice_company_name", payload.getInvoiceCompanyName());
        model.addAttribute("invoice_customer_name", payload.getInvoiceCustomerName());
        model.addAttribute("invoice_num", payload.getInvoiceNumber());
        model.addAttribute("invoice_date", payload.getInvoiceNumber());
        model.addAttribute("invoice_bill_total_amount", payload.getInvoiceTotalAmount());
        model.addAttribute("invoice_company_address", payload.getInvoiceCompanyAddress());

        subDataInItems(model, payload.getInvoiceBillItems());

        return "email_invoice.html";
    }





    private void subDataInItems(Model model, List <Map <String, String>> invoiceBillItems) {

        for (int i = 0; i < invoiceBillItems.size(); i++) {
            String itemNameKey = invoiceBillItems.get(i).keySet().toArray()[0].toString();
            switch (i) {
                case 0:
                    model.addAttribute("invoice_bill_item1", itemNameKey);
                    model.addAttribute("invoice_bill_item1_price", invoiceBillItems.get(i).get(itemNameKey));
                    break;
                case 1:
                    model.addAttribute("invoice_bill_item2", itemNameKey);
                    model.addAttribute("invoice_bill_item2_price", invoiceBillItems.get(i).get(itemNameKey));
                    break;
                case 2:
                    model.addAttribute("invoice_bill_item3", itemNameKey);
                    model.addAttribute("invoice_bill_item3_price", invoiceBillItems.get(i).get(itemNameKey));
                    break;
            }
        }
    }
}
//    private void executePost() {
//
//        /**
//         *
//         * This is going to setup the REST server configuration in the applicationContext
//         * you can see that I am using the new Spring's Java Configuration style and not some OLD XML file
//         *
//         */
//        ApplicationContext context = new AnnotationConfigApplicationContext(APIController.class);
//        /**
//         *
//         * We now get a RESTServer bean from the ApplicationContext which has all the data we need to
//         * log into the REST service with.
//         *
//         */
//        RESTServer mRESTServer = context.getBean(RESTServer.class);
//        /**
//         *
//         * Setting up data to be sent to REST service
//         *
//         */
//        Map <String, String> vars = new HashMap<String, String>();
//        vars.put("id", "JS01");
//        /**
//         *
//         * Doing the REST call and then displaying the data/user object
//         *
//         */
//        try {
//            /*
//                This is code to post and return a user object
//             */
//            RestTemplate rt = new RestTemplate();
//            rt.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
//            rt.getMessageConverters().add(new StringHttpMessageConverter());
//            String uri = new String("http://" + mRESTServer.getHost() + ":8080/springmvc-resttemplate-test/api/{id}");
//
//        } catch (HttpClientErrorException e) {
//            /**
//             *
//             * If we get a HTTP Exception display the error message
//             */
//        } catch (Exception e) {
//        }
//    }

