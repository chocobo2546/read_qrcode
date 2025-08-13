package th.co.scgp.qrcodeservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface QrCodeService {
    String readQrCode(MultipartFile file) throws Exception;

}
