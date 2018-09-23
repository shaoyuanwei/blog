package com.syw.blog.ptool;

public class GeneralViews {

    /**
     * use in error views
     */
    public interface IErrorView{};

    /**
     * use in success views
     */
    public interface INormalView extends IErrorView{};

}
