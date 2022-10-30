import java.util.*;
import java.awt.*;
import javax.swing.*; 
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class GoogleMail
{
  private File outputfile;
  private String recipient;

  public void screenshot(NewJPanel panel)
  {
    try
    {
    BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
    panel.paint(img.getGraphics());
    outputfile = new File("saved.png");
    ImageIO.write(img, "png", outputfile);
    }
    catch (Exception x)
    {
      System.out.println("trouble with screenshot");
    }
  }

  public void setRecipient(String r){
    recipient = r;
  }

  public String getRecipient() {return recipient;}


  public void send(NewJPanel panel)
  {
    // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    String from = "idgen.message@gmail.com"; // Sender's email
    String host = "smtp.gmail.com";
    // Get system properties
    // Properties properties = System.getProperties();
    // // Setup mail server
    // properties.put("mail.smtp.host", host);
    // properties.put("mail.smtp.port", "465");
    // properties.put("mail.smtp.ssl.enable", "true");
    // properties.put("mail.smtp.auth", "true");
    Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    // Get the Session object.// and pass 
    Session session = Session.getInstance(props, new javax.mail.Authenticator()
    {
        protected PasswordAuthentication getPasswordAuthentication()
        {
          return new PasswordAuthentication("idgen.message@gmail.com", "idgen2005");
        }
    });
    //session.setDebug(true);
    try {
        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);
        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));
        // Set To: header field of the header.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        // Set Subject: header field
        message.setSubject("Student ID");
        Multipart multipart = new MimeMultipart();
        MimeBodyPart attachmentPart = new MimeBodyPart();
        MimeBodyPart textPart = new MimeBodyPart();
      try {
        screenshot(panel);
        attachmentPart.attachFile(outputfile);
        textPart.setText("Your ID card has been processed.");
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(attachmentPart);
      } catch (IOException e) {
        e.printStackTrace();
      }
      message.setContent(multipart);
      System.out.println("Sending...");
      try {Transport.send(message); System.out.println("Message sent successfully...."); }
      catch (javax.mail.SendFailedException exception) {
        System.out.println("Invalid Email. Please retry.");
      }
    } catch (MessagingException mex) {
        mex.printStackTrace();
    }
  }
}