package th.co.scgp.qrcodeservice.service.impl;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import th.co.scgp.qrcodeservice.service.QrCodeService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    @Override
    public String readQrCode(MultipartFile file) throws Exception {
        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        if (bufferedImage == null) {
            throw new IllegalArgumentException("Invalid image file.");
        }

        BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage))
        );

        Result result = new MultiFormatReader().decode(binaryBitmap);
        return result.getText();
    }
}
