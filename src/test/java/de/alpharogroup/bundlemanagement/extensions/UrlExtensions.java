package de.alpharogroup.bundlemanagement.extensions;

import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.controller.BundleApplicationsController;
import de.alpharogroup.collections.array.ArrayFactory;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ArrayUtils;

@UtilityClass
public class UrlExtensions
{

	public static String getBaseUrl(@NonNull final String protocol, @NonNull final String host, int serverPort, @NonNull final String restVersion, @NonNull final String restPath)
	{
		return protocol
			+ "://"
			+ host
			+ ":" + serverPort + restVersion
			+ restPath;
	}

	public static String generateUrl(@NonNull final String baseUrl, @NonNull final String methodUrl, final String[] requestParams){
		return generateUrl(baseUrl, methodUrl, requestParams, "", ArrayFactory.newArray());
	}

	public static String generateUrl(@NonNull final String baseUrl, @NonNull final String methodUrl){
		return generateUrl(baseUrl, methodUrl, ArrayFactory.newArray());
	}

	public static String generateUrl(@NonNull final String baseUrl, @NonNull final String methodUrl, final String[] requestParams,  @NonNull final String arrayParamsName, final String[] arrayParams){
		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl).append(methodUrl);
		if(ArrayUtils.isNotEmpty(requestParams)){
			sb.append("?");
		} else {
			return sb.toString();
		}
		int count = 0;
		for(String requestParam: requestParams){
			sb.append(requestParam);
			sb.append("={");
			sb.append(requestParam);
			sb.append("}");
			count++;
			if( count < requestParams.length){
				sb.append("&");
			}
		}
		if(ArrayUtils.isNotEmpty(arrayParams)){
			sb.append("&");
		} else {
			return sb.toString();
		}
		count = 0;
		for(String arrayParam: arrayParams){
			sb.append(arrayParamsName);
			sb.append("=");
			sb.append(arrayParam);
			count++;
			if( count < arrayParams.length){
				sb.append("&");
			}
		}
		return sb.toString();
	}
}
