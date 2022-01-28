import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Triangle {
    private double a;
    private double b;
    private double c;
    private Type tipe=Type.undentife;

public enum Type{
    less90, more90, degree90, undentife
}
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

public boolean validTriangle(){
    double maxSide=Math.max(a, Math.max(b,c));
    return maxSide<(a+b+c-maxSide);
}
    public boolean positiveSide(){
        return a>0 && b>0 && c>0;
    }

public double countSquare(){
    if(!validTriangle()){
        throw new IllegalArgumentException("длина стороны не может превышать сумму двух других сторон");
    }
    if(!positiveSide()){
        throw new IllegalArgumentException("Длина сторон должна быть больше 0");
    }
    double perimetrForGeron=(a+b+c)/2;
    double square=Math.sqrt(perimetrForGeron*(perimetrForGeron-a)*(perimetrForGeron-b)*(perimetrForGeron-c));
    return square;
}
public void assertTypeTriangle(){
    if(!validTriangle()){
        throw new IllegalArgumentException("длина стороны не может превышать сумму двух других сторон");
    }
    if(!positiveSide()){
        throw new IllegalArgumentException("Длина сторон должна быть больше 0");
    }

    double alfa=Math.toDegrees(Math.acos((a*a+b*b-c*c)/(2*a*b)));
    double beta=Math.toDegrees(Math.acos((c*c+b*b-a*a)/(2*c*b)));
    double gamma=Math.toDegrees(Math.acos((c*c+a*a-b*b)/(2*c*a)));
    if(alfa==90 || beta==90 || gamma==90){
        setTipe(Type.degree90);}else{
    if(alfa>90 || beta>90 || gamma>90){
        setTipe(Type.more90);} else {
        setTipe(Type.less90);
    }
    }
    System.out.println(alfa);
    System.out.println(beta);
    System.out.println(gamma);
}
}


