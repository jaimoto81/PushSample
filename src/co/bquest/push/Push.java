package co.bquest.push;

import java.util.List;

import javax.management.Notification;

import org.json.JSONException;

import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.ResponsePacket;


public class Push {
 	
	//31273320b492508a569da2dd6151561f1cc28f54f3e688c9434256c395562267 - PassQlub
	//2ca699a8a733d7afdbd2fe0f402d760d51dcd16783ec6153b7118cd970e5a1ed - abril - iPad
	
	// f0db2176b3a515f34ea90998e641d14e86904274b1e4ba5ebe0c870fa8d4c03c - abril - iPhone Bquest
	//5a7d3a747736e6e29195619bc2bae5c8ef618a2b5608806d57ca6225e1d34304 - abril - iPhone Mono
	
	
	
	public static final String PATH2 = "/Users/jaimoto/Dropbox/bquest/Capacitacion/cert/PushPassqlub/PassQlubSSLCert.p12";
	public static final String PATH3 = "/Users/passbook/PushAbril/CertificatesSSL.p12";
	
	
	public static final String KEY_STORE_PWD = "BQuest2013";
	
    public static void main(String[] args) {
        
        try {
        	
        	PushNotificationPayload payload = PushNotificationPayload.complex();
        	payload.addAlert("#ViveAbril ");
        	payload.addBadge(1);
       
        	
        	List<PushedNotification> notifications= javapns.Push.payload(payload, PATH3, KEY_STORE_PWD, true, "5a7d3a747736e6e29195619bc2bae5c8ef618a2b5608806d57ca6225e1d34304");
        	for (PushedNotification notification : notifications) {
                if (notification.isSuccessful()) {
                        /* Apple accepted the notification and should deliver it */  
                    System.out.println("Push notification sent successfully to: " +
                                                    notification.getDevice().getToken());
                    /* Still need to query the Feedback Service regularly */  
                }
			 else {
                String invalidToken = notification.getDevice().getToken();
                /* Add code here to remove invalidToken from your database */  

                /* Find out more about what the problem was */  
                Exception theProblem = notification.getException();
                theProblem.printStackTrace();

                    /* If the problem was an error-response packet returned by Apple, get it */  
                ResponsePacket theErrorResponse = notification.getResponse();
                if (theErrorResponse != null) {
                        System.out.println(theErrorResponse.getMessage());
                }
        	}
            }
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (KeystoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

}
