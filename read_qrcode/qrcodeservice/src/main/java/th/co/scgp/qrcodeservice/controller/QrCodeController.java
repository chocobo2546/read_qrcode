package th.co.scgp.qrcodeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import th.co.scgp.qrcodeservice.service.QrDataParserService;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    @Autowired
    private QrDataParserService qrDataParserService;

    @PostMapping("/read")
    public String parseQrCode(@RequestParam("file") MultipartFile file) throws Exception {
        return qrDataParserService.parseQrData(file);
    }
}
