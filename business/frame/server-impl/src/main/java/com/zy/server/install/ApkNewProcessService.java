package com.zy.server.install;

//新进程启动该服务
public class ApkNewProcessService extends ApkBaseService {

    public ApkNewProcessService() {
        super();
    }

    @Override
    protected String getDiyTag() {
        return "ApkNewProcessService";
    }
}
