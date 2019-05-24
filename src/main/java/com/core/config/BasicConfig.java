package com.core.config;


public interface BasicConfig {

	/**
     * List of supported operating systems 
     */
    public static enum osList {

        WINDOWS
    }

    ;

    /**
     * List of websites in test scope
     */
    public enum selectSite {

    	admin_home
       
    }    
    ;
    
    /**
     * List of supported browsers
     */
    public enum selectedBrowser {

    	INTERNETEXPLORERREMOTE,GOOGLECHROME, FIREFOX, IE8, IE9,IE10,FIREFOXREMOTE, GOOGLECHROMEREMOTE, REMOTEWEBDRIVER ,SAFARI, SAFARIREMOTE,INTERNETEXPLORER10REMOTE,INTERNETEXPLORER9REMOTE,INTERNETEXPLORER8REMOTE
    };

}
