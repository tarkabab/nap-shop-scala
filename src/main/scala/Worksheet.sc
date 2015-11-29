import com.netaporter.shop.Catalogue
import com.netaporter.shop.Product

val p = "7,Piqué Polo shirt, £50.55".split(',')
val id = p(0).toInt
val name = p(1)
val price = BigDecimal(p(2).trim.drop(1))