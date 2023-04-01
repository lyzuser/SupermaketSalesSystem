
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import java.io.IOException;
public class SendMessage {
   public SendMessage()
   {
        int appid = 1400807058;
        String appkey = "424b14db6ca838027629d0644573e0cd";
        String[] phoneNumbers = {"18376295903"}; //手机号可以添很多。
        int templateId = 1746417;
        String smsSign = "sleepaday公众号";
        try {
            String[] params = {"您的"};  //第一个参数传递{1}位置想要的内容，第二个传递{2}的内容，以此类推。具体看步骤5
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, smsSign, "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

