package com.example.demo.controller;

import com.example.demo.model.Hexadecimal;
import org.apache.commons.codec.DecoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.DecodeException;

@RestController
@RequestMapping("/api")
public class HexController {
    @Autowired
    private Hexadecimal hexadecimal;
    @GetMapping("/encode/{text}")
    public String encode(@PathVariable String text) {
        return hexadecimal.encode(text);
    }

    @GetMapping("/decode/hextostring/{encodedText}")
    public String decode(@PathVariable String encodedText) throws DecoderException, DecodeException {
        return hexadecimal.decode(encodedText);
    }
}
