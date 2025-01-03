package com.sercan.ecommerce.database.mockdata

import com.sercan.ecommerce.database.entity.ProductEntity

object MockData {

    data class Banner(
        val id: Int,
        val title: String,
        val description: String,
        val discount: String,
        val imageUrl: String
    )

    val sampleProducts = listOf(
        ProductEntity(
            id = 1,
            name = "Nike Air Max",
            price = 2499.99,
            imageUrl = "https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/99486859-0ff3-46b4-949b-2d16af2ad421/custom-nike-air-max-90-by-you.png",
            description = "Nike Air Max spor ayakkabı, maksimum konfor ve şık tasarım.",
            category = "Ayakkabı",
            sizes = listOf("40", "41", "42", "43", "44"),
            colors = listOf("Siyah", "Beyaz", "Kırmızı")
        ),
        ProductEntity(
            id = 2,
            name = "Adidas Superstar",
            price = 1999.99,
            imageUrl = "https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/7ed0855435194229a525aad6009a0497_9366/Superstar_Ayakkabi_Beyaz_EG4958_01_standard.jpg",
            description = "Adidas Superstar, klasik tasarımıyla her tarza uygun.",
            category = "Ayakkabı",
            sizes = listOf("39", "40", "41", "42", "43"),
            colors = listOf("Beyaz", "Siyah", "Altın")
        ),
        ProductEntity(
            id = 3,
            name = "Puma RS-X",
            price = 1799.99,
            imageUrl = "https://images.puma.com/image/upload/f_auto,q_auto,b_rgb:fafafa,w_600,h_600/global/368845/01/sv01/fnd/TUR/fmt/png/RS-X-Efekt-Ayakkab%C4%B1",
            description = "Puma RS-X spor ayakkabı, retro tasarım modern teknoloji.",
            category = "Ayakkabı",
            sizes = listOf("39", "40", "41", "42", "43"),
            colors = listOf("Beyaz", "Mavi", "Gri")
        ),
        ProductEntity(
            id = 4,
            name = "New Balance 574",
            price = 2299.99,
            imageUrl = "https://nb.scene7.com/is/image/NB/ml574evg_nb_02_i?$$&wid=440&hei=440",
            description = "New Balance 574, rahatlık ve stil bir arada.",
            category = "Ayakkabı",
            sizes = listOf("40", "41", "42", "43", "44"),
            colors = listOf("Gri", "Lacivert", "Yeşil")
        ),
        ProductEntity(
            id = 5,
            name = "Oversize T-Shirt",
            price = 399.99,
            imageUrl = "https://cdn.dsmcdn.com/ty662/product/media/images/20221220/11/241476122/652095385/1/1_org_zoom.jpg",
            description = "Rahat kesim pamuklu oversize t-shirt.",
            category = "Giyim",
            sizes = listOf("S", "M", "L", "XL"),
            colors = listOf("Siyah", "Beyaz", "Gri")
        ),
        ProductEntity(
            id = 6,
            name = "Slim Fit Jean",
            price = 699.99,
            imageUrl = "https://cdn.dsmcdn.com/ty101/product/media/images/20210407/12/78068096/19117515/1/1_org_zoom.jpg",
            description = "Yüksek kalite slim fit kot pantolon.",
            category = "Giyim",
            sizes = listOf("30/32", "32/32", "34/32", "36/32"),
            colors = listOf("Mavi", "Siyah")
        ),
        ProductEntity(
            id = 7,
            name = "Basic Sweatshirt",
            price = 549.99,
            imageUrl = "https://cdn.dsmcdn.com/ty885/product/media/images/20230803/12/395485314/968078435/1/1_org_zoom.jpg",
            description = "Yumuşak dokulu basic sweatshirt.",
            category = "Giyim",
            sizes = listOf("S", "M", "L", "XL"),
            colors = listOf("Siyah", "Gri", "Lacivert")
        ),
        ProductEntity(
            id = 8,
            name = "Kapüşonlu Sweatshirt",
            price = 599.99,
            imageUrl = "https://cdn.dsmcdn.com/ty952/product/media/images/20230524/18/351630751/904786090/1/1_org_zoom.jpg",
            description = "Rahat kesim kapüşonlu sweatshirt.",
            category = "Giyim",
            sizes = listOf("S", "M", "L", "XL"),
            colors = listOf("Siyah", "Gri", "Bordo")
        ),
        ProductEntity(
            id = 9,
            name = "Deri Cüzdan",
            price = 449.99,
            imageUrl = "https://cdn.dsmcdn.com/ty252/product/media/images/20211125/11/1063213/10892435/2/2_org_zoom.jpg",
            description = "Hakiki deri erkek cüzdanı.",
            category = "Aksesuar",
            sizes = listOf("Standart"),
            colors = listOf("Kahverengi", "Siyah")
        ),
        ProductEntity(
            id = 10,
            name = "Güneş Gözlüğü",
            price = 899.99,
            imageUrl = "https://cdn.dsmcdn.com/ty545/product/media/images/20220916/18/177257191/576078858/1/1_org_zoom.jpg",
            description = "UV korumalı polarize güneş gözlüğü.",
            category = "Aksesuar",
            sizes = listOf("Standart"),
            colors = listOf("Siyah", "Gold")
        ),
        ProductEntity(
            id = 11,
            name = "Spor Ayakkabı",
            price = 899.99,
            imageUrl = "https://cdn.dsmcdn.com/ty957/product/media/images/20230526/11/352536088/889895437/1/1_org_zoom.jpg",
            description = "Rahat ve şık spor ayakkabı.",
            category = "Ayakkabı",
            sizes = listOf("39", "40", "41", "42", "43", "44"),
            colors = listOf("Beyaz", "Siyah", "Gri")
        ),
        ProductEntity(
            id = 12,
            name = "Akıllı Saat",
            price = 2499.99,
            imageUrl = "https://cdn.dsmcdn.com/ty102/product/media/images/20210419/12/80589662/65450521/1/1_org_zoom.jpg",
            description = "Fitness takibi ve bildirimler için akıllı saat.",
            category = "Elektronik",
            sizes = listOf("Standart"),
            colors = listOf("Siyah", "Gümüş", "Rose Gold")
        ),
        ProductEntity(
            id = 13,
            name = "Kot Pantolon",
            price = 399.99,
            imageUrl = "https://cdn.dsmcdn.com/ty558/product/media/images/20221018/18/196772508/595531740/1/1_org_zoom.jpg",
            description = "Slim fit kot pantolon.",
            category = "Giyim",
            sizes = listOf("28", "30", "32", "34", "36"),
            colors = listOf("Mavi", "Siyah", "Gri")
        ),
        ProductEntity(
            id = 14,
            name = "Bluetooth Kulaklık",
            price = 799.99,
            imageUrl = "https://cdn.dsmcdn.com/ty815/product/media/images/20230502/17/332362492/160765824/1/1_org_zoom.jpg",
            description = "Kablosuz bluetooth kulaklık.",
            category = "Elektronik",
            sizes = listOf("Standart"),
            colors = listOf("Siyah", "Beyaz")
        ),
        ProductEntity(
            id = 15,
            name = "Spor Çanta",
            price = 299.99,
            imageUrl = "https://cdn.dsmcdn.com/ty558/product/media/images/20221018/18/196772508/595531740/1/1_org_zoom.jpg",
            description = "Dayanıklı spor çantası.",
            category = "Spor",
            sizes = listOf("Standart"),
            colors = listOf("Siyah", "Lacivert", "Gri")
        ),
        ProductEntity(
            id = 16,
            name = "Makyaj Seti",
            price = 599.99,
            imageUrl = "https://cdn.dsmcdn.com/ty654/product/media/images/20221223/11/245670345/652895321/1/1_org_zoom.jpg",
            description = "Profesyonel makyaj seti.",
            category = "Kozmetik",
            sizes = listOf("Standart"),
            colors = listOf("Karma")
        ),
        ProductEntity(
            id = 17,
            name = "Oyun Konsolu",
            price = 12999.99,
            imageUrl = "https://cdn.dsmcdn.com/ty322/product/media/images/20220204/17/45048862/115823045/1/1_org_zoom.jpg",
            description = "Yeni nesil oyun konsolu.",
            category = "Elektronik",
            sizes = listOf("Standart"),
            colors = listOf("Siyah", "Beyaz")
        ),
        ProductEntity(
            id = 18,
            name = "Yoga Matı",
            price = 199.99,
            imageUrl = "https://cdn.dsmcdn.com/ty558/product/media/images/20221018/18/196772508/595531740/1/1_org_zoom.jpg",
            description = "Kaymaz yüzeyli yoga matı.",
            category = "Spor",
            sizes = listOf("Standart"),
            colors = listOf("Mor", "Mavi", "Pembe")
        ),
        ProductEntity(
            id = 19,
            name = "Kahve Makinesi",
            price = 3499.99,
            imageUrl = "https://cdn.dsmcdn.com/ty656/product/media/images/20221224/10/246163135/12755056/1/1_org_zoom.jpg",
            description = "Otomatik kahve makinesi.",
            category = "Ev Aletleri",
            sizes = listOf("Standart"),
            colors = listOf("Siyah", "Gümüş")
        ),
        ProductEntity(
            id = 20,
            name = "Kitaplık",
            price = 899.99,
            imageUrl = "https://cdn.dsmcdn.com/ty597/product/media/images/20221108/11/209583474/619491761/1/1_org_zoom.jpg",
            description = "Modern tasarım kitaplık.",
            category = "Mobilya",
            sizes = listOf("Standart"),
            colors = listOf("Ceviz", "Beyaz", "Antrasit")
        ),
        ProductEntity(
            id = 21,
            name = "Kargo Pantolon",
            price = 459.99,
            imageUrl = "https://cdn.dsmcdn.com/ty953/product/media/images/20230525/14/351809054/904961054/1/1_org_zoom.jpg",
            description = "Rahat kesim kargo pantolon",
            category = "Giyim",
            sizes = listOf("S", "M", "L", "XL", "XXL"),
            colors = listOf("Haki", "Siyah", "Bej")
        ),
        ProductEntity(
            id = 22,
            name = "Blazer Ceket",
            price = 899.99,
            imageUrl = "https://cdn.dsmcdn.com/ty547/product/media/images/20220917/1/177446565/576318125/1/1_org_zoom.jpg",
            description = "Şık tasarım blazer ceket",
            category = "Giyim",
            sizes = listOf("36", "38", "40", "42"),
            colors = listOf("Siyah", "Bej", "Lacivert")
        ),
        ProductEntity(
            id = 23,
            name = "Örme Kazak",
            price = 299.99,
            imageUrl = "https://cdn.dsmcdn.com/ty785/product/media/images/20230404/18/317131471/871891611/1/1_org_zoom.jpg",
            description = "Balıkçı yaka örme kazak",
            category = "Giyim",
            sizes = listOf("S", "M", "L", "XL"),
            colors = listOf("Ekru", "Gri", "Bordo", "Siyah")
        ),
        ProductEntity(
            id = 24,
            name = "Outdoor Bot",
            price = 1299.99,
            imageUrl = "https://cdn.dsmcdn.com/ty882/product/media/images/20230731/11/394431475/967024556/1/1_org_zoom.jpg",
            description = "Su geçirmez outdoor bot",
            category = "Ayakkabı",
            sizes = listOf("40", "41", "42", "43", "44", "45"),
            colors = listOf("Kahverengi", "Siyah")
        ),
        ProductEntity(
            id = 25,
            name = "Klasik Oxford",
            price = 899.99,
            imageUrl = "https://cdn.dsmcdn.com/ty537/product/media/images/20220910/11/173847775/566587118/1/1_org_zoom.jpg",
            description = "Deri oxford ayakkabı",
            category = "Ayakkabı",
            sizes = listOf("39", "40", "41", "42", "43", "44"),
            colors = listOf("Siyah", "Taba")
        ),
        ProductEntity(
            id = 26,
            name = "Kablosuz Mouse",
            price = 349.99,
            imageUrl = "https://cdn.dsmcdn.com/ty424/product/media/images/20220509/17/111361836/11322330/1/1_org_zoom.jpg",
            description = "Ergonomik kablosuz mouse",
            category = "Elektronik",
            sizes = listOf("Standart"),
            colors = listOf("Siyah", "Gri")
        ),
        ProductEntity(
            id = 27,
            name = "Mekanik Klavye",
            price = 1499.99,
            imageUrl = "https://cdn.dsmcdn.com/ty558/product/media/images/20221018/11/196570170/595276095/1/1_org_zoom.jpg",
            description = "RGB aydınlatmalı mekanik klavye",
            category = "Elektronik",
            sizes = listOf("Standart"),
            colors = listOf("Siyah", "Beyaz")
        ),
        ProductEntity(
            id = 28,
            name = "Koşu Taytı",
            price = 199.99,
            imageUrl = "https://cdn.dsmcdn.com/ty537/product/media/images/20220910/11/173847775/566587118/1/1_org_zoom.jpg",
            description = "Yüksek bel koşu taytı",
            category = "Spor",
            sizes = listOf("S", "M", "L"),
            colors = listOf("Siyah", "Lacivert", "Gri")
        ),
        ProductEntity(
            id = 29,
            name = "Pilates Topu",
            price = 129.99,
            imageUrl = "https://cdn.dsmcdn.com/ty666/product/media/images/20230106/23/254579046/669122775/1/1_org_zoom.jpg",
            description = "65cm pilates topu",
            category = "Spor",
            sizes = listOf("65cm"),
            colors = listOf("Mor", "Mavi", "Pembe")
        ),
        ProductEntity(
            id = 30,
            name = "Akıllı Bileklik",
            price = 799.99,
            imageUrl = "https://cdn.dsmcdn.com/ty198/product/media/images/20211014/22/146163981/261892517/1/1_org_zoom.jpg",
            description = "Nabız ölçer akıllı bileklik",
            category = "Elektronik",
            sizes = listOf("Standart"),
            colors = listOf("Siyah", "Mavi", "Pembe")
        ),
        ProductEntity(
            id = 31,
            name = "Tablet PC",
            price = 4999.99,
            imageUrl = "https://cdn.dsmcdn.com/ty825/product/media/images/20230524/14/351530061/893833431/1/1_org_zoom.jpg",
            description = "10.1 inç tablet bilgisayar",
            category = "Elektronik",
            sizes = listOf("Standart"),
            colors = listOf("Gri", "Siyah")
        ),
        ProductEntity(
            id = 32,
            name = "Protein Tozu",
            price = 699.99,
            imageUrl = "https://cdn.dsmcdn.com/ty594/product/media/images/20221106/12/208357897/618027150/1/1_org_zoom.jpg",
            description = "Whey protein tozu 1000gr",
            category = "Spor",
            sizes = listOf("1000gr"),
            colors = listOf("Standart")
        ),
        ProductEntity(
            id = 33,
            name = "Fitness Eldiveni",
            price = 149.99,
            imageUrl = "https://cdn.dsmcdn.com/ty252/product/media/images/20211125/11/1063213/10892435/2/2_org_zoom.jpg",
            description = "Ağırlık çalışmaları için eldiven",
            category = "Spor",
            sizes = listOf("S", "M", "L"),
            colors = listOf("Siyah", "Kırmızı")
        ),
        ProductEntity(
            id = 34,
            name = "Dumbell Set",
            price = 899.99,
            imageUrl = "https://cdn.dsmcdn.com/ty656/product/media/images/20221224/10/246163135/12755056/1/1_org_zoom.jpg",
            description = "Ayarlanabilir dumbell seti",
            category = "Spor",
            sizes = listOf("2x20kg"),
            colors = listOf("Siyah")
        ),
        ProductEntity(
            id = 35,
            name = "Ruj Seti",
            price = 299.99,
            imageUrl = "https://cdn.dsmcdn.com/ty455/product/media/images/20220609/11/123473631/496274708/1/1_org_zoom.jpg",
            description = "6'lı mat ruj seti",
            category = "Kozmetik",
            sizes = listOf("Standart"),
            colors = listOf("Karışık")
        ),
        ProductEntity(
            id = 36,
            name = "Fondöten",
            price = 399.99,
            imageUrl = "https://cdn.dsmcdn.com/ty662/product/media/images/20221220/11/241476122/652095385/1/1_org_zoom.jpg",
            description = "Uzun süre kalıcı fondöten",
            category = "Kozmetik",
            sizes = listOf("30ml"),
            colors = listOf("Açık", "Orta", "Koyu")
        ),
        ProductEntity(
            id = 37,
            name = "Göz Farı Paleti",
            price = 249.99,
            imageUrl = "https://cdn.dsmcdn.com/ty654/product/media/images/20221223/11/245670345/652895321/1/1_org_zoom.jpg",
            description = "18'li göz farı paleti",
            category = "Kozmetik",
            sizes = listOf("Standart"),
            colors = listOf("Karışık")
        ),
        ProductEntity(
            id = 38,
            name = "Parfüm",
            price = 899.99,
            imageUrl = "https://cdn.dsmcdn.com/ty653/product/media/images/20221223/9/245607015/652817461/1/1_org_zoom.jpg",
            description = "Kadın parfüm 100ml",
            category = "Kozmetik",
            sizes = listOf("100ml"),
            colors = listOf("Standart")
        ),
        ProductEntity(
            id = 39,
            name = "Cilt Bakım Seti",
            price = 599.99,
            imageUrl = "https://cdn.dsmcdn.com/ty654/product/media/images/20221223/11/245670345/652895321/1/1_org_zoom.jpg",
            description = "3'lü cilt bakım seti",
            category = "Kozmetik",
            sizes = listOf("Standart"),
            colors = listOf("Standart")
        ),
        ProductEntity(
            id = 40,
            name = "Güneş Kremi",
            price = 199.99,
            imageUrl = "https://cdn.dsmcdn.com/ty656/product/media/images/20221224/10/246163135/12755056/1/1_org_zoom.jpg",
            description = "SPF 50+ güneş kremi",
            category = "Kozmetik",
            sizes = listOf("50ml"),
            colors = listOf("Standart")
        ),
        ProductEntity(
            id = 41,
            name = "Deri Kemer",
            price = 299.99,
            imageUrl = "https://cdn.dsmcdn.com/ty252/product/media/images/20211125/11/1063213/10892435/2/2_org_zoom.jpg",
            description = "Hakiki deri erkek kemeri",
            category = "Aksesuar",
            sizes = listOf("90cm", "95cm", "100cm", "105cm"),
            colors = listOf("Kahverengi", "Siyah")
        ),
        ProductEntity(
            id = 42,
            name = "Kolye Set",
            price = 199.99,
            imageUrl = "https://cdn.dsmcdn.com/ty662/product/media/images/20221220/11/241476122/652095385/1/1_org_zoom.jpg",
            description = "3'lü kolye seti",
            category = "Aksesuar",
            sizes = listOf("Standart"),
            colors = listOf("Altın", "Gümüş")
        ),
        ProductEntity(
            id = 43,
            name = "Şal",
            price = 149.99,
            imageUrl = "https://cdn.dsmcdn.com/ty654/product/media/images/20221223/11/245670345/652895321/1/1_org_zoom.jpg",
            description = "İpek karışımlı şal",
            category = "Aksesuar",
            sizes = listOf("90x90"),
            colors = listOf("Bordo", "Lacivert", "Bej")
        ),
        ProductEntity(
            id = 44,
            name = "Çamaşır Makinesi",
            price = 12999.99,
            imageUrl = "https://cdn.dsmcdn.com/ty656/product/media/images/20221224/10/246163135/12755056/1/1_org_zoom.jpg",
            description = "9 kg çamaşır makinesi",
            category = "Ev Aletleri",
            sizes = listOf("Standart"),
            colors = listOf("Beyaz", "Gri")
        ),
        ProductEntity(
            id = 45,
            name = "Blender Set",
            price = 1299.99,
            imageUrl = "https://cdn.dsmcdn.com/ty662/product/media/images/20221220/11/241476122/652095385/1/1_org_zoom.jpg",
            description = "1000W blender seti",
            category = "Ev Aletleri",
            sizes = listOf("Standart"),
            colors = listOf("Kırmızı", "Siyah")
        ),
        ProductEntity(
            id = 46,
            name = "Tost Makinesi",
            price = 899.99,
            imageUrl = "https://cdn.dsmcdn.com/ty654/product/media/images/20221223/11/245670345/652895321/1/1_org_zoom.jpg",
            description = "Granit tost makinesi",
            category = "Ev Aletleri",
            sizes = listOf("Standart"),
            colors = listOf("Gri", "Kırmızı")
        ),
        ProductEntity(
            id = 47,
            name = "Ütü",
            price = 1499.99,
            imageUrl = "https://cdn.dsmcdn.com/ty656/product/media/images/20221224/10/246163135/12755056/1/1_org_zoom.jpg",
            description = "Buharlı ütü",
            category = "Ev Aletleri",
            sizes = listOf("Standart"),
            colors = listOf("Mavi", "Beyaz")
        )
    )

    val banners = listOf(
        Banner(
            id = 1,
            title = "Yaz İndirimleri",
            description = "Ürünlerde büyük fırsat",
            discount = "%50 İndirim",
            imageUrl = "https://img-lcwaikiki.mncdn.com/Resource/Images/Banner/230523-yenisezon-2.jpg"
        ),
        Banner(
            id = 2,
            title = "Elektronik",
            description = "Teknolojide dev kampanya",
            discount = "%40 İndirim",
            imageUrl = "https://www.vatanbilgisayar.com/upload/banner/ANASAYFA-NOTEBOOK-BANNER-MAYIS_95165.jpg"
        ),
        Banner(
            id = 3,
            title = "Spor",
            description = "Spor ürünlerinde fırsat",
            discount = "%30 İndirim",
            imageUrl = "https://contents.mediadecathlon.com/s1007450/k$3ad382e3d76352c1f1bbc40e2ea67cc3/spor-ayakkabi-banner.jpg"
        ),
        Banner(
            id = 4,
            title = "Kozmetik",
            description = "Kozmetikte süper fırsatlar",
            discount = "%25 İndirim",
            imageUrl = "https://www.gratis.com/medias/Gratis-Cilt-Bakim-Urunleri-Kampanya-1920x600.jpg"
        ),
        Banner(
            id = 5,
            title = "Moda",
            description = "Modada sezon sonu",
            discount = "%60 indirim",
            imageUrl = "https://statics.boyner.com.tr/bannerupload/kampanya/2024/1/15/moda_202401151743.jpg"
        )
    )
}