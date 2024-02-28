import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import odh.database.OdhTransactionInfor

WS.comment("STT: " + stt)
OdhTransactionInfor.ENGAGEMENT_RANK = engagementRank
OdhTransactionInfor.IID = iid
CustomKeywords.'odh.database.OdhDatabase.updateEngagementStatusData'(OdhTransactionInfor.ENGAGEMENT_RANK, OdhTransactionInfor.IID)

/*
 * case này test manual do thời gian đồng bộ lên insider rất lâu
 * golive sẽ tự động => 1ngay/1lan update
 */
