package org.wso2.licencemgr;

import java.io.IOException;

import mail.send.SendMail;

public class LMdbNotifierMain {

        public static void main(String[] args) throws IOException {
            SendMail.SendMailLM();
        }

    }