/**
 * openImaDis - Open Image Discovery: Image Life Cycle Management Software
 * Copyright (C) 2011-2016  Strand Life Sciences
 *   
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.strandgenomics.imaging.iclient.dialogs;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import com.strandgenomics.imaging.iclient.util.ConfigurationException;
import com.strandgenomics.imaging.iclient.util.ConnectionPreferences;

/**
 * The Model that powers LoginDialog
 * @author arunabha
 *
 */
public class LoginDocomentModel {

	/** server address */
	protected Document serverIP   = null;
	/** server port */
	protected Document serverPort = null;
	/** server login */
	protected Document loginName  = null;
	/** login password */
	protected Document password   = null;
	/** proxy server address */
	protected Document proxyHost  = null;
	/** proxy port */
	protected Document proxyPort  = null;
	/** proxy username */
	protected Document proxyUser  = null;
	/** proxy user password */
	protected Document proxyPassword = null;
	/** checks whether to use proxy */
	protected boolean useProxy = false;
	/** checks whether to use ssl */
	protected boolean useSSL = false;
	//protected int protocol = 0;

	public LoginDocomentModel() 
	{
		serverIP   = new PlainDocument();
		serverPort = new PlainDocument();
		loginName  = new PlainDocument();
		password   = new PlainDocument();
		proxyHost  = new PlainDocument();
		proxyPort  = new PlainDocument();
		proxyUser  = new PlainDocument();
		proxyPassword = new PlainDocument();
		//initializes the fields
		initialize();
		//initializes the document listeners
		initializeListeners();
	}
	
	/**
	 * initializes the model with the underlying connection preference object
	 * @see ConnectionPreferences
	 */
	protected void initialize()
	{
		ConnectionPreferences pref = ConnectionPreferences.getInstance();

		useSSL = pref.sslEnabled();
		useProxy = pref.toUseProxy();

		try
		{
			if (pref.getHostAddress() != null)
				serverIP.insertString(0, pref.getHostAddress(), null);
			if (pref.getHostPort() != null)
				serverPort.insertString(0, pref.getHostPort().toString(), null);
			if (pref.getLoginName() != null)
				loginName.insertString(0, pref.getLoginName(), null);
			if (pref.getPassword() != null)
				password.insertString(0, pref.getPassword(), null);
			if (pref.getProxyAddress() != null)
				proxyHost.insertString(0, pref.getProxyAddress(), null);
			if (pref.getProxyPort() != null)
				proxyPort.insertString(0, pref.getProxyPort().toString(), null);
			if (pref.getProxyUser() != null)
				proxyUser.insertString(0, pref.getProxyUser(), null);
			if (pref.getProxyPassword() != null)
				proxyPassword.insertString(0, pref.getProxyPassword(), null);
		} 
		catch (BadLocationException bad) //not likely to happen here
		{}
	}
	
	/**
	 * initializes various document listers to the fields, so to fire appropriate model events
	 * to the UI when the individual fields are entered/edited
	 */
	protected void initializeListeners() {
		// TODO Auto-generated method stub
		serverIP.addDocumentListener(new DocumentListener(){

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	/**
	 * validate and set the connection preferences. This methods throws ConfigurationException
	 * with relevant messages if certain fields are not populated with relevant information
	 * @see ConnectionPreferences
	 * @throws ConfigurationException if the fields are not populated properly
	 */
	public void validateAndSetParameters() throws ConfigurationException 
	{
		if (validateIPAddress(getServerAddress()) == null) {
			throw new ConfigurationException("Configuration Error",
					"Illegal host internet address ", getServerAddress());
		}

		if (getServerPort() == null) {
			throw new ConfigurationException("Configuration Error",
					"Illegal Server Port ", getFieldValue(serverPort));
		}

		if (getLogin() == null) {
			throw new ConfigurationException("Configuration Error",
					"Login name cannot be empty ", getLogin());
		}

		if (getPassword() == null) {
			throw new ConfigurationException("Configuration Error",
					"Password field cannot be empty ");
		}

		ConnectionPreferences pref = ConnectionPreferences.getInstance();
		String sv = getServerAddress();
		if(sv.toLowerCase().startsWith("https"))
				useSSL = true;
		else if(sv.toLowerCase().startsWith("http"))
				useSSL = false;
		pref.setServerSettings(getServerAddress(), getServerPort(), getLogin(),getPassword(), useSSL);
		pref.save();

		// now set proxy settings in the system properties
		if (useProxy) 
		{
			if (validateIPAddress(getProxyServerAddress()) == null) 
			{
				throw new ConfigurationException("Configuration Error",
						"Illegal Proxy Address", getProxyServerAddress());
			}

			if (getProxyPort() == null)
			{
				throw new ConfigurationException("Configuration Error",
						"Illegal Proxy Port ", getFieldValue(proxyPort));
			}
		}

		pref.setProxySettings(getProxyServerAddress(), getProxyPort(), getProxyUser(), getProxyPassword(), useProxy);
		pref.save();
	}

	private String getFieldValue(Document doc) 
	{
		try 
		{
			return trim(doc.getText(0, doc.getLength()));
		} 
		catch (BadLocationException e) 
		{
			return null;
		}
	}

	public void setProxyEnabled(boolean status) {
		useProxy = status;
	}
	
	public boolean useProxy() {
		return useProxy;
	}

	public boolean useSSL() {
		return useSSL;
	}

	public String getServerAddress() {
		return getFieldValue(serverIP);
	}

	public Integer getServerPort() {
		return validatePort(getFieldValue(serverPort));
	}

	public String getLogin() {
		return getFieldValue(loginName);
	}

	public String getPassword() {
		return getFieldValue(password);
	}
	
	public String getProxyServerAddress() {
		return getFieldValue(proxyHost);
	}

	public Integer getProxyPort() {
		return validatePort(getFieldValue(proxyPort));
	}

	public String getProxyUser() {
		return getFieldValue(proxyUser);
	}

	public String getProxyPassword() {
		return getFieldValue(proxyPassword);
	}

	private String validateIPAddress(String str)
	{
		URI uri = null;
		String url = trim(str);
		try {
			uri = new URI(url);
			String domain = uri.getHost();
			if(domain!=null){
				str = domain.startsWith("www.") ? domain.substring(4) : domain;
				
			}
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		String hostAddress = null;
		try {
			String host = trim(str);
			if (host == null)
				return null;

			InetAddress ip = InetAddress.getByName(host);
			if (ip != null) {
				hostAddress = host;
			}
		} 
		catch (Exception ex) 
		{}
		return hostAddress;
	}

	private String trim(String str)
	{
		if (str == null)
			return null;

		str = str.trim(); // trim ends
		if (str.length() == 0) {
			str = null;
		}

		return str;
	}

	private Integer validatePort(String str)
	{
		try
		{
			String port = trim(str);
			if (port == null)
				return null;
			return Integer.parseInt(port);
		} 
		catch (Exception ex)
		{}
		return null;
	}

	public Document getServerAddressField() {
		return serverIP;
	}

	public Document getServerPortField() {
		return serverPort;
	}

	public Document getLoginField() {
		return loginName;
	}

	public Document getPasswordField() {
		return password;
	}

	public Document getProxyServerAddressField() {
		return proxyHost;
	}

	public Document getProxyPortField() {
		return proxyPort;
	}

	public Document getProxyUserField() {
		return proxyUser;
	}

	public Document getProxyPasswordField() {
		return proxyPassword;
	}	
}
