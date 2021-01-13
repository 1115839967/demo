package com.example.demo.utils;

import lombok.Data;

/**
 * @author xutiancheng
 * @since 2021-01-13 13:42
 */
@Data
public class Remote {

    private String user = "root";
    private String host = "127.0.0.1";
    private int port = 22;
    private String password = "";
    private String identity = "~/.ssh/id_rsa";
    private String passphrase = "";
}