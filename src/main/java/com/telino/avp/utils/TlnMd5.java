package com.telino.avp.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



/**
*
* @author Absil Romain
*/
public class TlnMd5
{
	/**
	 * Conversion bytes en chaine
	 */
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) 
	{
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
   /**
    * Create a object that will makes imprints of messages, with a specified 
    * algorithm.
    * @param algorithm the algorithm you want to use (see constants).
    **/
   public TlnMd5(String algorithm)
   {
       try
       {
           this.algorithm = MessageDigest.getInstance(algorithm);
       } 
       catch (NoSuchAlgorithmException ex)
       {
           ex.printStackTrace();
       }
   }

   /** 
    * Gets the inprint of the wanted file inputFileName and write in in the 
    * file outputFileName.
    * @param inputFileName the file you want the inprint.
    * @param outputFileName the file you want to write the inprint in.
    **/
   public void getInprint(String inputFileName, String outputFileName)
       throws IOException
   {
       PrintWriter out = null;
       try
       {
           out = new PrintWriter(outputFileName);
           out.println(computeDigest(loadBytes(inputFileName)));
       }
       finally
       {
           if(out != null)
               out.close();
       }
   }

   private byte[] loadBytes(String inputFileName) throws IOException
   {
       FileInputStream in = null;
       try
       {
          in = new FileInputStream(inputFileName) ;
          ByteArrayOutputStream buffer = new ByteArrayOutputStream();
          int ch;
          while( (ch = in.read()) != -1)
              buffer.write(ch);
          return buffer.toByteArray();
       }
       finally
       {
           if(in != null)
               in.close();
       }
   }

   public String computeDigest(byte[] b)
   {
       algorithm.reset();
       algorithm.update(b);
       byte[] hash = algorithm.digest();
       String d = "";
       for (int i = 0; i < hash.length; i++)
       {
           int v = hash[i] & 0xFF;
           if(v < 16)
               d += "0";
           d += Integer.toString(v, 16).toUpperCase();            
       }

       return d;
   }        

   private MessageDigest algorithm;
   /**
    * Correspond à l'algorithme de hachage SHA-1.
    **/
   public static final String SHA1 = "SHA-1";
   /**
    * Correspond à l'algorithme de hachage MD5.
    **/
   public static final String MD5 = "MD5";

   public static String GetAlgo (String sAlgo)
   {
   	if (sAlgo.equalsIgnoreCase(SHA1))
   		return SHA1;
   	
   	if (sAlgo.equalsIgnoreCase(MD5))
   		return MD5;
   	
   	return null;
   }


   public static String getHashByte (String sHash, byte[] vByte)
   {
   	String sAlgo = GetAlgo (sHash);
   	if (sAlgo == null)
   		return  null;
   	
       TlnMd5 vTask = new TlnMd5 (sAlgo);
   	String sDst = vTask.computeDigest(vByte);
   	return sDst;
   }



   public static String getHashStr (String sHash, String sDta)
   {
   	String sAlgo = GetAlgo (sHash);
   	if (sAlgo == null)
   		return  null;
   	
       TlnMd5 vTask = new TlnMd5 (sAlgo);
   	String sDst = vTask.computeDigest(sDta.getBytes());
   	return sDst;
   }

   public static String getHashFile (String sHash, String sFileSrc, String sFileDst)
   {
   	String sAlgo = GetAlgo (sHash);
   	if (sAlgo == null)
   		return  null;
   	
       TlnMd5 vTask = new TlnMd5 (sAlgo);
       try
       {
       	vTask.getInprint(sFileSrc, sFileDst);
       }
       catch(Exception e)
       {
       	System.out.println(e.toString());
       }

   	return sFileDst;
   }


   public static String getHashFile (String sHash, String sFileSrc)
   {
   	String sAlgo = GetAlgo (sHash);
   	if (sAlgo == null)
   		return  null;
   	
   	String sHashFile = "";
       try
       {
           InputStream fis =  new FileInputStream(sFileSrc);

           byte[] buffer = new byte[8096];
           MessageDigest complete = MessageDigest.getInstance(sHash);
           int numRead;

           do {
               numRead = fis.read(buffer);
               if (numRead > 0) {
                   complete.update(buffer, 0, numRead);
               }
           } while (numRead != -1);

           fis.close();

           byte[] b = complete.digest();
           String result = "";

           for (int i=0; i < b.length; i++) {
               result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
           }
           return result;
       }
       catch (Exception e)
       {
       	System.out.println(e.toString());
       }

   	return sHashFile;
   }


   public static String StrMd5 (String sMd5Src)
   {
       TlnMd5 vTask = new TlnMd5 (TlnMd5.MD5);
   	String sMd5 = vTask.computeDigest(sMd5Src.getBytes());
   	return sMd5;
   }
}