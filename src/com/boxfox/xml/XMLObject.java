package com.boxfox.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLObject {
	private StringOutputStream out;
	private Properties props;
	
	public XMLObject(){
		out = new StringOutputStream();
		props = new Properties();
	}
	
	public XMLObject(String str){
		this();
		InputStream in = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
		try {
			props.loadFromXML(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String get(String key){
		return props.getProperty(key);
	}
	
	public XMLObject put(String key, String value){
		props.setProperty(key, value);
		return this;
	}
	
	@Override
	public String toString(){
		out.clear();
        try {
            props.storeToXML(out, "XMLObject","UTF-8");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
		return out.getString();
	}
	
	private class StringOutputStream extends OutputStream {
	    private StringBuilder mBuf;
	    
	    public StringOutputStream(){
	        mBuf = new StringBuilder();
	    }
	 
	    @Override
	    public void write(int arg0) throws IOException {
	        mBuf.append((char) arg0);
	    }
	 
	    public String getString() {
	        return mBuf.toString();
	    }
	    
	    public void clear(){
	    	mBuf = new StringBuilder();
	    }
	 
	}

}
