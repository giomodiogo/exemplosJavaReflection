package aula.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import aula.reflection.annotations.MyDBFieldName;
import aula.reflection.annotations.MyInvoke;
import aula.reflection.annotations.MyNotNull;
import aula.reflection.entities.Aluno;
import aula.reflection.entities.Pessoa;

public class MainAnnotation {

	public static void main(String[] args) {
		try {
			// Recupera objeto da classe
			Class classAluno = Aluno.class;
			// Recupera campos
			Field[] fields = classAluno.getDeclaredFields();

			// Verifica em quais dos campos possui a annotation.
			for (Field field : fields) {
				MyNotNull annotation = field.getAnnotation(MyNotNull.class);
				if (annotation != null) {
					System.out.println("Campo com annotation MyNotNull: " + field.getName());
				}
			}

			// Verifica em quais dos campos possui a annotation.
			for (Field field : fields) {
				MyDBFieldName annotation = field.getAnnotation(MyDBFieldName.class);
				if (annotation != null) {
					System.out.println("Mapeando campos DB: " + field.getName() + " -> " + annotation.name());
				}
			}

			// Recupera os metodos.
			Method[] methods = classAluno.getDeclaredMethods();
			Constructor constructorAluno = classAluno.getConstructor();
			// Cria objeto.
			Pessoa pessoa = (Pessoa) constructorAluno.newInstance();
			// Verifica qual metodo possui a annotation e executa
			for (Method method : methods) {
				MyInvoke annotation = method.getAnnotation(MyInvoke.class);
				if (annotation != null) {
					System.out.println("Invocando método com Annotation:");
					method.invoke(pessoa);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
