package th.co.scgp.qrcodeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import th.co.scgp.qrcodeservice.service.QrCodeService;
import th.co.scgp.qrcodeservice.service.impl.QrDataParserImpl;
//import th.co.scgp.qrcodeservice.service.impl.TransformQrCodeService;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    @Autowired
    private QrCodeService qrCodeService;

    @Autowired
    private QrDataParserImpl qrDataParserService;

    @PostMapping("/read")
    public String readQrCode(@RequestParam("file") MultipartFile file) throws Exception {
        return qrCodeService.readQrCode(file);
    }

    @PostMapping("/transform")
    public String transformQrCode(@RequestParam("file") MultipartFile file) throws Exception {
        String qrText = qrCodeService.readQrCode(file);
        return qrDataParserService.parseQrData(qrText);
    }
}
