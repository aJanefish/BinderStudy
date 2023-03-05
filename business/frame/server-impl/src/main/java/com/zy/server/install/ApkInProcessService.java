package com.zy.server.install;

//本进程的服务
public class ApkInProcessService extends ApkBaseService {

    public ApkInProcessService() {
        super();
    }


    @Override
    protected String getDiyTag() {
        return "IPService";
    }
}
