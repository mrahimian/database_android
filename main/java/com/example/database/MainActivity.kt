package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity(),View.OnFocusChangeListener {
    lateinit var goods : Array<String>
    lateinit var separateGoods : Array<String>
    lateinit var goodsCodes : Array<String>
    lateinit var cars : Array<String>
    lateinit var brands : Array<String>
    lateinit var carNameField : AutoCompleteTextView
    lateinit var goodsNameField : AutoCompleteTextView
    lateinit var goodsCodeField : AutoCompleteTextView
    lateinit var brandNameField : AutoCompleteTextView
    lateinit var carCodeField : EditText
    lateinit var brandCodeField : EditText
    lateinit var keyCode : EditText
    lateinit var match : HashMap<String,String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "KotlinApp"


        goods = arrayOf("ابزارآلات","اکسسوری جانبی خارجات ، بوگیر ، روکش صندلی ، اسپری",
            "قفل","لوازم اسپرت","روشنایی","nan","بدنه اصلی آرم ، رکاب ","برف پاک کن پمپ شیشه شور","چراغ","nan","رینگ، لاستیک و متعلقات",
            "سپر و متعلقات","شیشه و آینه","گلگیر و متعلقات","قطعات داخلی اتاق آمپر ، بوق ","قطعات برقی داخل موتور پمپ ، وایر ، استارت ، دینام ، موتور فن ، واتر پمپ ",
            "قطعات برقی مصرفی لامپ خودرو ، سوکت ها ، پاور گریپ ، کابل ، سیم جات ، فیوز ، سیم کشی ، باتری ","داشبورد دنده ، فرمان ، ایربگ","سایر","صندلی","موکت کف پایی ها ",
            "جعبه دنده کلاج ، دیسک و صفحه ، گیربکس ، خلاص کن ، صفحه کلاج","خنک کننده موتور رادیاتور ","قطعات اگزوز و توربوشارژ",
            "قطعات سوخت رسانی کوئل ، سوزن ، دریچه هوا ، لوازم گازی ، شمع ، شلنگ ها ، لوله جات ، انژکتور ",
            "قطعات کولر و بخاری شلنگ جات","قطعات موتوری کاسه نمد ، شلنگ جات ، بوش ها ، طبق ، دسته موتور ، گردگیر جات (این باید جزو جلوبندی باشد ) پیچ و مهره ، فولی ، سر میل لنگ ، موتوری ، یاتاقان ، تسمه ، بلبرینگ ، سرسیلندر ، سوپاپ ، واشر ، دسته موتور ، زنجیر ، استوپر ، غلطک ",
            "لوازم ترمز لنت ، استوپر ترمز ، پمپ ترمز ","لوازم تعلیق جلوبندی ، زیر بندی ، کمک فنر ، گردگیر جات ، لاستیک چاکدار ، موج گیر ، پلوس ","روغن","سایر گریس ","فیلتر","مکمل و سیالات")
        separateGoods = arrayOf("ابزارآلات","اکسسوری جانبی خارجات "," بوگیر "," روکش صندلی "," اسپری قفل","لوازم اسپرت","روشنایی","nan","بدنه اصلی آرم "," رکاب ",
            "برف پاک کن پمپ شیشه شور","چراغ","nan","رینگ"," لاستیک و متعلقات"," سپر و متعلقات","شیشه و آینه","گلگیر و متعلقات","قطعات داخلی اتاق آمپر "," بوق ","قطعات برقی داخل موتور پمپ ",
            " وایر "," استارت "," دینام "," موتور فن "," واتر پمپ "," قطعات برقی مصرفی لامپ خودرو "," سوکت ها "," پاور گریپ "," کابل "," سیم جات "," فیوز "," سیم کشی "," باتری ",
            "داشبورد دنده "," فرمان "," ایربگ","سایر","صندلی","موکت کف پایی ها "," جعبه دنده کلاج "," دیسک و صفحه "," گیربکس "," خلاص کن "," صفحه کلاج","خنک کننده موتور رادیاتور ",
            "قطعات اگزوز و توربوشارژ"," قطعات سوخت رسانی کوئل "," سوزن "," دریچه هوا "," لوازم گازی "," شمع "," شلنگ ها "," لوله جات "," انژکتور "," قطعات کولر و بخاری شلنگ جات",
            "قطعات موتوری کاسه نمد "," شلنگ جات "," بوش ها "," طبق "," دسته موتور "," گردگیر جات (این باید جزو جلوبندی باشد ) پیچ و مهره "," فولی "," سر میل لنگ "," موتوری "," یاتاقان ",
            " تسمه "," بلبرینگ "," سرسیلندر "," سوپاپ "," واشر "," دسته موتور "," زنجیر "," استوپر "," غلطک "," لوازم ترمز لنت "," استوپر ترمز "," پمپ ترمز ","لوازم تعلیق جلوبندی ",
            " زیر بندی "," کمک فنر "," گردگیر جات "," لاستیک چاکدار "," موج گیر "," پلوس ","روغن","سایر گریس ","فیلتر","مکمل و سیالات")
        goodsCodes = arrayOf("A1", "A2", "A3", "A4", "B5", "C6", "D7", "D8", "D9", "D10", "D11", "D12", "D13", "D14", "E15", "E16", "E17", "F18", "F19", "F20", "F21", "G22", "G23", "G24", "G25", "G26", "G27", "G28", "G29", "H30", "H31", "H32", "H33")

        matchGoods()

        brands = arrayOf("ابتی بلت","اپتی بلت","اتو داینو","اتوفیکس","اتوکر","اتولایت","اتومکانیک","اس اس ای تی","اس اف آر","اس ان آر","اس ان تی","اس کی اف","استار",
            "استارمکس","استرانگ","استنلی","استیکو","استیل میت","استیلا","استیم پارت","اسرام","اسمارت","اسنا یدک","اطلس","اف آ گ","افرا پارت","اکتیو","اکتیو تولز","اکسلرا","اگزیدی","الایت",
            "التکس","الیگ","ام اس ای کو","ام ال اس","ام بی سی","ام جی","ام کی کاشیاما کورپ","ام وی ام","امیرنیا","ان تی ان","ان تی کی"
            ,"ان جی","ان جی کی","انرجایزر","او ای ام","اوتالدی","اوتل","اوربیتال-وان","ای اس ام","ای ام","ای ام سی","ای تی اس","ایران تایر",
            "ایران خودرو","ایساکو","ایگ لایت","ایگل","اینکو","اینهل","آپولو","آچیلس","آدور گسکت","آراد مکث","آرام‌اس","آروا","آریا کیان سپهر","آریا کیان سپهر","آژینه پاد",
            "آستون","آسیا لنت","آکبونو","آکویل استار","آی اس پی کو","آی ال سی","آی ان آ","آیسر","آی‌سون","آیسین","آیسین ادویکس","بارز","باسئوس","باندو","باورس","برلیانس",
            "بریجستون","بسفیتس","بلک اند دکر","بندیکس","بوتو","بورگ جرمنی","بوستر",
            "بوش","بوش","بولزوان","بهنام موتور راد","بی ام دبلیو","بیترون","بی‌کی‌اس کو.","بیلگین","بینلانگ","پاتیگ","پارس نیکان","پاسیکو","پتلاس","پرادو","پرتو صنعت آریا",
            "پرتو وایر","پرفکت","پروماتیکس","پژو","پلاستکس","پلینگا","پوشیتا","پولی هوس","پوما","پومکس","پی اچ سی والیو","پی ار ایکس","پی ال","پیتیکو","پیرلی","تاروکس","تایوان",
            "تراینگل","تسنیلا","تکستار","توان","توبیز","توتال","توربو","توسن","تویوتا-لکسوس جنیون پارتس","تویوتا جنیون پارتس","تویولکس","تی ار دبلیو","تی جی ال","تی کی","تیگار","تیمکو","جک",
            "جک موتورز","جنرال الکتریک","جی اس اس","جی ام بی","جی تی","جی تی رادیال","جیلی","جینیو","چانگان","چائویانگ","چری","چمپیون","چنس","حامین پارت","دانگیل","دانلوپ","دایکو",
            "دپو","دنا","دنسو","دوو","دوون","رابین","رادیاتور ایران","رایا","رایکالتون","رایکام","رنو","رودستون","رولندز","رونیکس","رویال","زبرا","زمردیدک","زیمر","زیمنس","سام","سامنس","سانتکس",
            "سانگ یانگ","سایپا","سپاهان باطری","سرمنتکس","سفیر","سکو","سودیکو","سوزوکی","سومیتومو","سونار","سونیک","سی اند آی","سی تی آر","سی لی","سیتروین","سیلور","سیمتایر","سیمیران","سیناپس",
            "شایان صنعت","شبستری","شرکت بلبرینگ تبریز ایرانیان","شرکت تولیدی قطعات جلوبندی خودرو ایران لاهیجان","شرکت ماشین کاران اراک","شمع نور","شیلدر","صامو پرشین","صبا باتری","فارسان","فالکن","فرانکو",
            "فرودو","فولکس واگن","فونیکس","فیلیپس","کابل خودرو سبزوار","کابل کنترل سپهر","کاپسن","کاترپیلار","کادیا","کارتک","کاسپین","کاوج","کایابا","کلاکس کار","کنتلار","کنتیننتال","کنزاکس",
            "کوآویس","کورتکو","کومهو تایر","کویر تایر","کویو","کویین","کهن سازه تچرا","کی استار","کی جی","کیا","کیا جنیون پارتس","کینگ استیل","گتس","گریت وال","گرین","گلد","گلداستار",
            "گلدستون","گلدن مگ","لاسا","لاستیک گیلان","لنت پارس","لوسینی","لوک","لیا لنت","لیان اتو پارت","لیتک","لیفان","لینگ لانگ","مارپا","مارشال","مارشال","ماندو","مایسان ماندو","متریکس",
            "متفرقه","متل","محک","مرسا لنت","مرسدس بنز","مزدا","معیار","مکسس","مگا تولز","مگنت مارلی","موبیس","مهرخواه","میتسوبوشی","میتسوبیشی","میرا","میشلین","ناب","ناچی","ناروا","نکسن",
            "نوتاش","نووا","نیسان","نیسان جنیون پارتس","نیلو خودرو","نئولوکس","واترفال","واکتا","والئو","وایر ایران","وجودی","وست لیک","وینکس","هاتچینسون","هاردکس","هامان","هانکوک","های-کیو",
            "های لنت","هلا","هوندا","هیپو","هیوندای","هیوندای جنیون اکسسوری","هیوندای جنیون پارتز","یزد تایر","یوتا","یوشیتا","یوکوهاما")
        cars = arrayOf("ام جی ZS","ام جی RX5","ام جی GT","ام جی GS","ام جی 6","ام جی 550","ام جی 360","ام جی 350","ام جی 3","کراس","ام وی ام V5","ام وی ام X55","ام وی ام X33S","ام وی ام X33","ام وی امX22","ام وی ام 550","ام وی ام 530","ام وی ام 315 هاچ بک","ام وی ام 315 صندوق دار","ام وی ام 110 S","ام وی ام 110","میتو","جولیتا","آلفتا","آلفارومئو 4C","H330","H320","H2L","H230","H220","نام خودرو","ون بنز","بنز کلاس SLR","بنز کلاس SLS","بنز کلاس SLK","بنز کلاس SLC","بنز کلاس SL","بنز کلاس SE","بنز کلاس S کوپه","بنز کلاس S","بنز کلاس ML","بنز کلاس GLK","بنز کلاس GLE","بنز کلاس GLC","بنز کلاس GLA","بنز کلاس GL","بنز کلاس G","بنز کلاس E کوپه","بنز کلاس E کروک","بنز کلاس E","بنز کلاس CLS","بنز کلاس CLK کوپه","بنز کلاس CLK کروک","بنز کلاس CLA","بنز کلاس CL","بنز کلاس CE","بنز کلاس C کوپه","بنز کلاس C","بنز کلاس B","بنز کلاس A","بنز GTS","بنز GL550","بی ام و سری M","بی ام و سری i","بی ام و سری 8","بی ام و سری 7","بی ام و سری 6 گرن کوپه","بی ام و سری 6 کوپه","بی ام و سری6 کروک","بی ام و سری 5 سدان","سری 5 GT","سری 4 گرن کوپه","بی ام و سری 4 کوپه","بی ام و سری 4 کروک","بی ام و سری 3 کروک","بی ام و سری 3 کوپه","بی ام و سری 3 سدان","بی ام و سری 3 GT","بی ام و سری 2 کوپه","بی ام و سری 2 کروک","بی ام و سری 2 اکتیوتورر","بی ام و سری 1 هاچ بک","بی ام و سری 1 کوپه","بی ام و سری 1 کروک","بی ام و Z4","بی ام و Z3","بی ام و X6","بی ام و X5","بی ام و X4","بی ام و X1","بی ام و X3","بی وای دی S7","بی وای دی S6","بی وای دی F3","پاژن دودر","پاژن چهاردر","پراید151","141","132","پراید131","پراید111","ویرا","جن تو","ایمپین","پژوRD","روآ","پژو پارس","پژو504","پژو407","پژو405","پژو301","پژو207","پژو206 SD","پژو206","پژو205","پژو204","پژو2008","ماکان","کیمن s","کاین","پانامرا","باکستر","پورشه 918","پورشه 911","وانت","سدان","تیبا هاچ بک","تیبا صندوق دار","یاریس","هایلوکس","هایس","ون تویوتا","لندکروز","کمری هیبرید","کمری","کریسیدا","کرولا","سکویا","تویوتا RAV 4","پریوس","پرادو","اف جی کروز","اریون","تیوتا C-HR هیبرید","تیوتا GT 86","گرند","ریفاین","جک جی 5","جک جی 4","جک جی 3 هاچ بک","جک جی 3 سدان","اسپرت","جک S5","جک S3","دنده ای","امگرند X7","امگرند RV-7","امگرند 8","امگرند 7","اتوماتیک","جیلی GC6","جیلی","none","none","none","none","none","none","none","ایدو","چانگان CS 35","ویانا","تیگو7","تیگو5","آریزو 6","آریزو5 TE","آریزو5","چری A15","دانگ فنگ S30","H30 کراس","دنا پلاس","دنا معمولی","نوبیرا","ماتیز","سیلو","ریسر","اسپرو","دی اس 7 کراس بک","دی اس 6","دی اس 5LS","دی اس 5","دی اس 4 کراس بک","دی اس 3","رانا LX","رانا EL","مگان","کولئوس","کپچر","فلوئنس","ساندرو استپ وی","ساندرو","داستر","تندر90","تندر وانت","تندر 90 استیشن","تالیسمان","پی کی","پارس تندر","اسکالا کروک","اسکالا","رنو GTL9","رنو5","رنو3","رنو21","نیو کوراندو","نیو اکتیون","موسو","کوراندو","رکستون","چیرمن","تیوولی","اکتیون","ساینا اتوماتیک","ساینا دنده ای","سمند سریر","سورن","سمند (LX و EL و...)","گرند ویتارا","کیزاشی","سوییفت","سامورایی","ژیان","زانتیا","سیتروئن DS21","سیتروئن C5","سیتروئن C3","گلف","گل","کمپر","کدی","کارمن","شیراکو","سانتانا","جتا","تیگوان","ترنسفورمر","ترنسپورتر","پولو","پاسات","بیتل کروک","بیتل","باگی","فولکس T3-VANAGON","فولکس T2-COMBI","فولکس LT","کاپرا دو کابین","کاپرا تک کابین","کوئیک دنده ای","کوئیک اتوماتیک","موهاوی","کارنز","کادنزا","سورنتو","سراتو کوپ","سراتو","ریو وارداتی","ریو","رتونا","پیکانتو","اینترپرایز","اسپورتیج","اپیروس","اپتیما هیبرید","اپتیما","هاوال M4","هاوال H6","وینگل 6","وینگل 5","وینگل 3","گریت وال C30","لکسوس SC","لکسوس RX هیبرید","لکسوس RX","لکسوس RC","لکسوس NX هیبرید","لکسوس NX","لکسوس LX","لکسوس LS","لکسوس IS کروک","لکسوس IS","لکسوس GX","لکسوس GS","لکسوسES","لکسوس CT","لندمارک V7","لیفان X60","لیفان X50","لیفان 820","لیفان 620","لیفان 520i","مزدا CX9","مزدا CX5","مزدا 929","مزدا 808","مزدا 626","مزدا 6","مزدا 323","مزدا 3 جدید هاچ بک","مزدا 3 جدید صندوق دار","مزدا 3","مزدا 2","ون میتسوبیشی","میراژ","مگنا","لنسر","گالانت","پاجرو","اوتلندر","اکلیپس","میتسوبیشی GT3000","میتسوبیشی ASX","وانت زامیاد","مورانو","ماکسیما","قشقایی","سرانزا","رونیز","جوک","تی ینا","پیکاپ","پاترول","ایکس تریل","آلتیما","آروان","نیسان GT-R","آمازون","ولوو XC90","ولوو XC60","ولوو XC40","ولوو V40","ولوو S90","ولوو S60","ولوو C70 کروک","ولوو C30","ولوو244","لجند","سیویک","پریلود","اینتگرا","آکورد","HONDA S800","HONDA HR-V","HONDA CR-X","HONDA CR-V","ولستر","ورنا","وراکروز (ix55)","کوپه","سوناتا هیبرید","سوناتا","سنتینیال","سانتافه ( ix45)","جنسیس کوپه","جنسیس","توسان ( ix35)","اکسنت","استارکس","آوانته","آزرا (گرنجور)","هیوندا i40","هیوندا i30","هیوندا i20","هیوندا i 10","هیوندا H1","هیوندا H350","لتیتود","سیمبل","سفران","انواع مدل ها","انواع مدل ها","آریسان","انواع مدل ها","انواع مدل ها","انواع مدل ها","ون کاسپین","ون هایما","ون دلیکا")
        carNameField = findViewById(R.id.car_name)
        goodsNameField = findViewById(R.id.goods_name)
        brandNameField = findViewById(R.id.brand_name)
        carNameField.onItemClickListener = AdapterView.OnItemClickListener{
                parent,view,position,id->
            val selectedItem = parent.getItemAtPosition(position).toString()
            // Display the clicked item using toast
            carCodeField.setText((findCode(cars, selectedItem)+1).toString())

        }
        goodsNameField.onItemClickListener = AdapterView.OnItemClickListener{
                parent,view,position,id->
            val selectedItem = parent.getItemAtPosition(position).toString()
            val index : Int = find(selectedItem)
            // Display the clicked item using toast
            goodsNameField.setText(goods[index])
            goodsCodeField.setText(goodsCodes[index])

        }
        brandNameField.onItemClickListener = AdapterView.OnItemClickListener{
                parent,view,position,id->
            val selectedItem = parent.getItemAtPosition(position).toString()
            // Display the clicked item using toast
            brandCodeField.setText((findCode(brands, selectedItem)+1).toString())

        }

        carCodeField = findViewById(R.id.car_code)
        goodsCodeField = findViewById(R.id.goods_code)
        goodsCodeField.onItemClickListener = AdapterView.OnItemClickListener{
                parent,view,position,id->
            var text : String = parent.getItemAtPosition(position).toString()
            var index = goodsCodes.indexOf(text)
            goodsNameField.setText(goods[index])
        }
        brandCodeField = findViewById(R.id.brand_code)
        carCodeField.setOnFocusChangeListener(this)
        goodsCodeField.setOnFocusChangeListener(this)
        brandCodeField.setOnFocusChangeListener(this)

        val goodsCodeAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, goodsCodes)
        goodsCodeField.setAdapter(goodsCodeAdapter)

        val carAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, cars)
        carNameField.setAdapter(carAdapter)

        val goodsAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, separateGoods)
        goodsNameField.setAdapter(goodsAdapter)

        val brandAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, brands)
        brandNameField.setAdapter(brandAdapter)

        keyCode = findViewById(R.id.key_code)
    }

    fun search (v : View){
        val key = keyCode.text.toString()
        val parts : List<String> = key.split(";")
        var intent : Intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("carName",carNameField.text.toString())
        intent.putExtra("carCode",carCodeField.text.toString())
        intent.putExtra("goodsName",goodsNameField.text.toString())
        intent.putExtra("goodsCode",goodsCodeField.text.toString())
        intent.putExtra("brandName",brandNameField.text.toString())
        intent.putExtra("brandCode",brandCodeField.text.toString())
        intent.putExtra("key",parts.toTypedArray())
        startActivity(intent)
    }


    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (!hasFocus) {
            if (!(v as EditText).text.toString().equals("")){
                if (v != null) {
                    try {
                    if (v.id == carCodeField.id) {
                        carNameField.setText(cars[carCodeField.text.toString().toInt() - 1])
                    } else if (v.id == brandCodeField.id) {
                        brandNameField.setText(brands[brandCodeField.text.toString().toInt() - 1])
                    }
                    else if(v.id == goodsCodeField.id){
                        var text : String = v.text.toString()
                        var index = goodsCodes.indexOf(text)
                        goodsNameField.setText(goods[index])
                    }
                }catch(e : Exception){}
                }
            }else{

            }
        }
    }

    fun findCode(args: Array<String> , arg : String) : Int {
         for (i in args){
             if (i.equals(arg)){
                 return args.indexOf(i)
             }
         }
        return 0
    }

    fun matchGoods(){
        var h = goods.size
        var g = goodsCodes.size
        match = hashMapOf()
        for (i in 0..goods.size-1){
            match.put(goodsCodes.get(i),goods.get(i))
        }
    }

    fun find(item : String) : Int {
        for (i in 0..goods.size-1){
            val arg : String = goods.get(i)
            if (arg.contains(item.trim())){
                return i
            }
        }
        return 0
    }
}
