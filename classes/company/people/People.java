package classes.company.people;


import java.util.Calendar;
/**
*@author Jesus Torralbo
*/

abstract public class People{
    //Atributos
    protected String name;
	protected String surname;
	protected String dni;
	protected Calendar birthday;
	protected String nationality;



    protected People(String name, String surname, String dni, Calendar birthday, String nationality)throws Exception{
        this.name=name;
        this.surname=surname;
        this.dni=People.setDni(dni);
        this.birthday=birthday;
        this.nationality=nationality;
    }

    /**Comprueba si el dni es valido
    @param dni String con el dni a comprobar
    @return String con el DNI
    @throws Exception Lanza una excepcion si el dni no es valido
    */
    private static String setDni(String dni)throws Exception{
        if(People.checkDni(dni)==true){
            return dni;
        }else{
            throw new Exception("DNI no valido");
        }
    }

    //GETTER
	public String getName(){
		return this.name;
	}
	public String getSurname(){
		return this.surname;
	}
	public String getDni(){
		return this.dni;
	}
	public Calendar getBirthday(){
		return this.birthday;
	}
	public String getNationality(){
		return this.nationality;
	}
	//SETTER
	public void setName(String n){
		this.name=n;
	}
	public void setSurname(String s){
		this.surname=s;
	}
	public void setNationality(String nat){
		this.nationality=nat;
	}
	public void setBirthday(Calendar b){
		this.birthday=b;
	}

    /**
    * Metodo que comprueba la validez de un DNI
    * @param dni Recibe un dni de 9 caracteres
    * @return Devuelve un booleano de verdadero o falso
    * @throws Exception Lanza una excepcion en caso de error inesperado
    */
    public static boolean checkDni(String dni)throws Exception{
		boolean set=false;

		if (dni.length()==9){
			if(Character.isDigit(dni.charAt(0))&&Character.isDigit(dni.charAt(1))
               &&Character.isDigit(dni.charAt(2))&&Character.isDigit(dni.charAt(3))
               &&Character.isDigit(dni.charAt(4))&&Character.isDigit(dni.charAt(5))
               &&Character.isDigit(dni.charAt(6))&&Character.isDigit(dni.charAt(7))
               &&Character.isLetter(dni.charAt(8))){

                    String miWord=""+dni.charAt(8);

			if(miWord.toUpperCase().equals(People.dniWord(dni.substring(0,8)))){
				set=true;
			}
			}else{
                throw new Exception("Numero o letra incorrectos");
            }
		}else{
            throw new Exception("Formato de dni invalido");
        }
		return set;
	}

    /**
    * Metodo para la comprobacion de que la letra del dni corresponde al numero
    * @param dni Recibe el dni introducido
    * @return Devuelve la eltra correspondiente al numero recibido
    */
    private static String dniWord(String dni){
		int myDNI = Integer.parseInt(dni.substring(0,8));
        int rest = 0;
        String miWord = "";
        String[] availablesWord = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

        rest = myDNI % 23;

        miWord = availablesWord[rest];

        return miWord;
	}


	@Override
    public String toString(){
    	return this.name+" "+this.surname+" con DNI "+this.dni;
    }





}
