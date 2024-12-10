package ca.gbc.notificationservice.service;

import ca.gbc.notificationservice.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    //After configuring your properties, you should no longer see any errors
    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "order-placed")
    public void listen(OrderPlacedEvent orderPlacedEvent){
       log.info("Received message from order-placed topic {}", orderPlacedEvent );

       //send email to customer
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper =  new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("comp3095@georgebrown.ca");
            messageHelper.setTo(orderPlacedEvent.getEmail());
            messageHelper.setSubject(String.format("Your Order (%s) was placed successfully",
                    orderPlacedEvent.getOrderNumber()));
            messageHelper.setText(String.format("""
                    Good Day,
                    
                    Your Order with order number %s was successfully placed
                    
                    Thank-you for your business
                    COMP3095 Staff                    
                    """,
                    orderPlacedEvent.getOrderNumber()));
        };

        try{

            javaMailSender.send(messagePreparator);
            log.info("Order Notification email sent!!");

        }catch(MailException e){
            log.error("Exception occurred when sending mail", e);
            throw new RuntimeException("Exception occurred when sending email", e);

        }


    }

}
