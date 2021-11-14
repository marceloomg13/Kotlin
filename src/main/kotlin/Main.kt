import kotlin.random.Random

fun main(args: Array<String>) {
    var modulo=Modulo(15)
    for (i in 0..2){
        for (j in 0..14){
            modulo.establecerNota(j,i,Random.nextFloat()*10)
            println(modulo.evaluaciones[i][j])
        }
    }
    modulo.calculaEvaluacionFinal()
    //modulo.listaNotas(2)
}
class Modulo(maxAlumnos:Int){
    var alumnos:Array<Alumno?> = arrayOfNulls(maxAlumnos)
    var evaluaciones:Array<FloatArray> = Array(4) { FloatArray(15) }

    fun establecerNota(idAlumno:Int, evaluacion:Int, nota:Float){
        evaluaciones[evaluacion][idAlumno]=nota
    }
    fun calculaEvaluacionFinal(){
        var media:Float= 0.0F
        for (i in 0..14){
            for(j in 0..2){
                media += evaluaciones[j][i]
            }
            media /= 3
            evaluaciones[3][i]=media
            media=0.0F
        }
    }
    fun listaNotas(evaluacion:Int):List<Pair<Int,Float>>{
        var lista:List<Pair<Int,Float>> = listOf()
        for(i in 0 until evaluaciones[evaluacion].size) {
            lista.plus(Pair(i, evaluaciones[evaluacion][i]))
        }
        return lista
    }
    fun numeroAprobados(evaluacion:Int): Int{
        var aprobados=0
        for(i in 0 until evaluaciones[evaluacion].size){
            if (evaluaciones[evaluacion][i]>=5) aprobados++
        }
        return aprobados
    }
    fun notaMasBaja(evaluacion:Int): Float{
        var notamasbaja=0.0F
        for(i in 0 until evaluaciones[evaluacion].size){
            if (evaluaciones[evaluacion][i]<=notamasbaja) notamasbaja=evaluaciones[evaluacion][i]
        }
        return notamasbaja
    }
    fun notaMasAlta(evaluacion:Int): Float{
        var notamasalta=0.0F
        for(i in 0 until evaluaciones[evaluacion].size){
            if (evaluaciones[evaluacion][i]>=notamasalta) notamasalta=evaluaciones[evaluacion][i]
        }
        return notamasalta
    }
    fun notaMedia(evaluacion:Int): Float{
        var notamedia=0.0F
        for(i in 0 until evaluaciones[evaluacion].size){
            notamedia+=evaluaciones[evaluacion][i]
        }
        notamedia=notamedia/evaluaciones[evaluacion].size-1
        return notamedia

    }
    fun hayAlumnosConDiez(evaluacion:Int): Boolean{
        var algun10 = false
        for(i in 0 until evaluaciones[evaluacion].size) if (evaluaciones[evaluacion][i] == 10.0F) algun10 = true
        return algun10
    }
    fun hayAlumnosAprobados(evaluacion:Int): Boolean{
        var algunAprovado = false
        for(i in 0 until evaluaciones[evaluacion].size) if (evaluaciones[evaluacion][i] >= 5.0F) algunAprovado = true
        return algunAprovado
    }
    fun primeraNotaNoAprobada(evaluacion:Int): Float{
        var Primera=0.0F
        for(i in 0 until evaluaciones[evaluacion].size) if (evaluaciones[evaluacion][i] < 5.0F) return evaluaciones[evaluacion][i]
        return Primera
    }
    fun matricularAlumno(alumno:Alumno){
        alumnos.plus(alumno)
    }
    fun bajaAlumno(idAlumno: Int){
       // alumnos[alumnos.indexOfFirst{idAlumno}]=null
    }
}

class Alumno(Nombre:String){
    private var idAlumno:Int=1
    private var nombreCompleto:String
    init {
        this.nombreCompleto = Nombre
        idAlumno++
    }
}