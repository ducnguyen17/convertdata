package com.example.demo.controller;
import com.example.demo.load.TransRequest;
import com.example.demo.model.ConvertAdapter;
import com.example.demo.model.Hexadecimal;
import org.apache.commons.codec.DecoderException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ConvertController {
    private final ConvertAdapter convertAdapter;
    private final Hexadecimal hexadecimal;

    public ConvertController(ConvertAdapter convertAdapter, Hexadecimal hexadecimal) {
        this.convertAdapter = convertAdapter;
        this.hexadecimal = hexadecimal;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/{from}/{to}")
    ResponseEntity<Object> convert(@PathVariable(value = "from") String from, @PathVariable(value = "to") String to,
                                   @RequestBody  TransRequest transRequest) throws DecoderException {
        String textData = transRequest.getData();
        String result = null;
        switch (from){
            case "String" : {
                if(to.equals("Base64")) {
                    result = convertAdapter.convertStringToBase64(textData);
                }else if(to.equals("Hex")){
                    result = convertAdapter.convertStringToHexa(textData);
                } else if (to.equals("Byte")) {
                    result = Arrays.toString(convertAdapter.convertStringToByte(textData));
                } else if (to.equals("String")) {
                    result = convertAdapter.convertStringToString(textData);
                }
            }break;
            case "Base64": {
                if(to.equals("Hex")){
                    result = convertAdapter.convertBase64ToHexa(textData);
                } else if (to.equals("String")) {
                    result = convertAdapter.convertBase64ToString(textData);
                } else if (to.equals("Byte")) {
                    result = Arrays.toString(convertAdapter.convertBase64ToByte(textData));
                }
            }break;
            case "Hex":{
                if(to.equals("Base64")){
                    result = convertAdapter.convertHexaToBase64(textData);
                } else if (to.equals("String")) {
                    result = convertAdapter.convertHexadecimalToString(textData);
                }
                else if(to.equals("Byte")){
                    result = Arrays.toString(convertAdapter.convertHexadecimalToByteArray(textData));
                } else if(to.equals("Hex")) {
                    result = convertAdapter.convertHexToSameLengthHex(textData);
                }
            }break;
            case "Byte":{
                if(to.equals("Hex")){
                    result = convertAdapter.convertStringToHexa(textData);
                } else if (to.equals("String")) {
                    byte[] byteArray = convertAdapter.convertStringToByte(textData);
                    result = ConvertAdapter.convertByteToString(byteArray);
                } else if (to.equals("Base64")) {
                    result = convertAdapter.convertBase64ToBase64(textData);
                } else if (to.equals("Byte")) {
                    result = Arrays.toString(convertAdapter.convertByteArrayToSameByteArray(textData));
                }
            }break;
            default:
                throw  new DecoderException("Loại dữ liệu cần chuyển đổi, hiện tại không hợp lệ !");
        }
        return ResponseEntity.ok().body(Map.of("result", result));
    }

}
