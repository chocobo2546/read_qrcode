package th.co.scgp.qrcodeservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import th.co.scgp.qrcodeservice.service.QrCodeService;
import th.co.scgp.qrcodeservice.service.QrDataParserService;
import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class QrDataParserImpl implements QrDataParserService {

    @Autowired
    private QrCodeService qrCodeService;

    @Override
    public String parseQrData(MultipartFile file) throws Exception {
        JSONObject resultObject = new JSONObject();
        String text = qrCodeService.readQrCode(file);

        int index = 0;
        while (index < text.length()) {
            String tag = text.substring(index, index + 2);
            String lenStr = text.substring(index + 2, index + 4);
            int length = Integer.parseInt(lenStr);
            String value = text.substring(index + 4, index + 4 + length);

            if (tag.equals("00")) {
                int subIndex = 0;
                while (subIndex < value.length()) {
                    String subTag = value.substring(subIndex, subIndex + 2);
                    String subLenStr = value.substring(subIndex + 2, subIndex + 4);
                    int subLength = Integer.parseInt(subLenStr);
                    String subValue = value.substring(subIndex + 4, subIndex + 4 + subLength);

                    if (subTag.equals("01")) {
                        resultObject.put("sendingBank", subValue);
                    } else if (subTag.equals("02")) {
                        resultObject.put("transRef", subValue);
                    }

                    subIndex += 4 + subLength;
                }
            }

            index += 4 + length;
        }
        return resultObject.toString();
    }
}
