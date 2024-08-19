import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.text.NumberFormat
import java.util.Locale



WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.btnproperti.co.id/tools/hitung-harga-properti')

WebUI.takeScreenshot()

// Input data
WebUI.setText(findTestObject('HitungProperti/Page_Hitung Harga Properti Maksimal  BTN Properti  Indonesia/input_penghasilan'),
		penghasilan)
	
WebUI.setText(findTestObject('HitungProperti/Page_Hitung Harga Properti Maksimal  BTN Properti  Indonesia/input_pengeluaran'),
		pengeluaran)
	
WebUI.selectOptionByValue(findTestObject('HitungProperti/Page_Hitung Harga Properti Maksimal  BTN Properti  Indonesia/jangka_waktu'),
		waktu, true)
	
WebUI.takeScreenshot()

//hitung rumus
int input_penghasilan = penghasilan.toInteger()
int input_pengeluaran = pengeluaran.toInteger()
int jangka_waktu = waktu.toInteger()

int hasil_hitung_rumus = (input_penghasilan - input_pengeluaran) * (jangka_waktu *12/3)
WebUI.comment("hasil hitung rumus " + hasil_hitung_rumus)

// Memformat hasil perhitungan menjadi format rupiah
NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"))
String format_hasil = currencyFormatter.format(hasil_hitung_rumus)
WebUI.comment("hasil format " + format_hasil)

// Menghilangkan simbol mata uang bawaan dan menggantinya dengan 'Rp'
String hasil = format_hasil.replace("Rp", "Rp ").replace(",00", "")
WebUI.comment("hasil akhir" + hasil)

if (input_penghasilan<=input_pengeluaran ) 
{
 WebUI.comment("penghasilan lebih kecil dari pengeluaran") 
}

else {
WebUI.click(findTestObject('HitungProperti/Page_Hitung Harga Properti Maksimal  BTN Properti  Indonesia/button_hitung'))
String hasil_hitung_web = WebUI.getText(findTestObject('HitungProperti/Page_Hitung Harga Properti Maksimal  BTN Properti  Indonesia/hasil_hitung'))

WebUI.comment("hasil hitung web " + hasil_hitung_web)
WebUI.verifyEqual(hasil_hitung_web, hasil)

}
WebUI.takeScreenshot()
WebUI.closeBrowser()

