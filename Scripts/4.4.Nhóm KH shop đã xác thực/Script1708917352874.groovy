import com.kms.katalon.core.util.KeywordUtil


'Compare ODH with AutoMKT'
CustomKeywords.'odh.database.CompareDataFile.compareData'(ODH_FilePath, AutoMKT_FilePath)

'Compare AutoMKT with insider'
CustomKeywords.'odh.database.CompareDataFile.compareData'(AutoMKT_FilePath, Insider_FilePath)









