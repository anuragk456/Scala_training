implicit class EnhancedInt(val x: Int) {
  def #%(discount: Int): Int = x - (x * discount / 100)
  def rangeFinder():String = {
    if(x>=0 && x <=1000)
        return "0-1000"
    else if(x>1000)
        return "1000+"
    else 
        return "negative"
  }
}

@main  def Runit = {
val result:Int = 500 #% 20
println(result)
println(result.rangeFinder())
}