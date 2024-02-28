import org.junit.Assert

import com.kms.katalon.core.util.KeywordUtil

import amg.database.AmgDatabase
import amg.database.InsiderOutCardWhiteListModel
import auto.utilities.database.DatabaseExecutor
import groovy.sql.GroovyRowResult

KeywordUtil.logInfo("Verify line: "+stt)

InsiderOutCardWhiteListModel modelObj = new InsiderOutCardWhiteListModel()
modelObj.setIid(a_unique_user_id)
modelObj.setWhitelist_product(a_c_whitelist_product)
modelObj.setApproval_status(a_c_approval_status)
modelObj.setTime(a_c_time)

CustomKeywords.'amg.database.AmgDatabase.verifyInsiderOutCardWhiteList'(modelObj)
