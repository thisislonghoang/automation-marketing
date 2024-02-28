package auto.commons

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS


/**
 * Web service common functions
 * @author ChungND1
 *
 */
public class WSCommons {
	/**
	 * Call Rest Service and log info
	 * @param requestObject
	 * @return ResponseObject
	 */
	@Keyword
	public static ResponseObject callRestService(RequestObject requestObject) {
		try {

			WS.comment("Rest Url: "+ requestObject.getRestUrl())

			WS.comment("Rest Request: "+ requestObject.httpBody)

			ResponseObject response = WS.sendRequest(requestObject,FailureHandling.CONTINUE_ON_FAILURE)

			WS.comment("Rest Status: "+response.getStatusCode())

			WS.comment("Rest Response: "+response.getResponseText())

			return response
		}catch(Exception ex){

			ex.printStackTrace()
		}
	}

	public static ResponseObject callRestService(String objectId,def data) {

		RequestObject requestObject = findTestObject(objectId,data)

		return callRestService(requestObject)
	}

	public static ResponseObject callRestService(String objectId) {

		RequestObject requestObject = findTestObject(objectId)

		return callRestService(requestObject)
	}

	public static ResponseObject callRestServiceWithoutRequestLog(String objectId,def data) {

		RequestObject requestObject = findTestObject(objectId,data)

		KeywordUtil.logInfo("Url: "+ requestObject.getRestUrl())

		ResponseObject response = WS.sendRequest(requestObject,FailureHandling.CONTINUE_ON_FAILURE)

		KeywordUtil.logInfo("Response: "+response.getResponseText())

		WS.verifyResponseStatusCode(response, 200,FailureHandling.CONTINUE_ON_FAILURE)

		return response
	}

	/**
	 * Call Rest Service and log info
	 * @param requestObject
	 * @return
	 */
	@Keyword
	public static ResponseObject callSoapService(RequestObject requestObject) {
		try {

			WS.comment("Soap Method: "+ requestObject.getSoapRequestMethod())

			WS.comment("WSDL Url: "+ requestObject.getWsdlAddress())

			WS.comment("Soap Service Function: "+ requestObject.getSoapServiceFunction())

			WS.comment("Soap Service Endpoint: "+ requestObject.getSoapServiceEndpoint())

			WS.comment("Soap Service Body: "+ requestObject.getSoapBody())

			ResponseObject response = WS.sendRequest(requestObject,FailureHandling.CONTINUE_ON_FAILURE)

			WS.comment("Soap Status: "+response.getStatusCode())

			WS.comment("Soap Response: "+response.getResponseText())

			return response
		}catch(Exception ex){

			ex.printStackTrace()
		}
	}
}
