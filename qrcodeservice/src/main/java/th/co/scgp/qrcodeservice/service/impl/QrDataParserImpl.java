package th.co.scgp.qrcodeservice.service.impl;

import org.springframework.stereotype.Service;
import th.co.scgp.qrcodeservice.service.QrDataParserService;

@Service
public class QrDataParserImpl implements QrDataParserService {

    @Override
    public String parseQrData(String input) {
        StringBuilder output = new StringBuilder();
        int index = 0;

        while (index < input.length()) {
            if (index + 4 > input.length()) break;

            String tag = input.substring(index, index + 2);
            int length;
            try {
                length = Integer.parseInt(input.substring(index + 2, index + 4));
            } catch (NumberFormatException e) {
                break;
            }

            if (index + 4 + length > input.length()) break;

            String value = input.substring(index + 4, index + 4 + length);

            if (tag.equals("01") || tag.equals("02")) {
                output.append(value).append("\n");
            }

            index += 4 + length;
        }
        return output.toString();
    }
}

