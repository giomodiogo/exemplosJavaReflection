package aula.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import aula.reflection.entities.Aluno;
import aula.reflection.entities.Pessoa;

public class Main {

	public static void main(String[] args) {
		try {
			/*
			 * Class Object
			 */
			// Objeto da classe
			Class classAluno = Aluno.class;
			Class classPessoa = Pessoa.class;

			/*
			 * Class Name
			 */

			// Nome completo com o pacote
			String className = classAluno.getName();
			System.out.println(className);

			// Nome simples
			String simpleClassName = classAluno.getSimpleName();
			System.out.println(simpleClassName);

			/*
			 * Modifiers
			 */
			// Retorna os modificadores da classe, codificado em Inteiro.
			System.out.println(classAluno.getModifiers());
			// Retorna os nomes dos modificadores
			System.out.println(Modifier.toString(classAluno.getModifiers()));

			// Verifica os modificadores da Classe.
			System.out.println(Modifier.isAbstract(classAluno.getModifiers()));
			System.out.println(Modifier.isPublic(classAluno.getModifiers()));
			System.out.println(Modifier.isProtected(classAluno.getModifiers()));

			/*
			 * Package Info
			 */
			System.out.println(classAluno.getPackage());

			/*
			 * Superclass
			 */
			System.out.println(classAluno.getSuperclass());
			System.out.println(classPessoa.getSuperclass());

			/*
			 * Implemented Interfaces
			 */
			System.out.println("Interfaces Aluno:");
			Class[] classAlunoInterfaces = classAluno.getInterfaces();
			print(classAlunoInterfaces);
			System.out.println("Interfaces Pessoa:");
			Class[] classPessoaInterfaces = classPessoa.getInterfaces();
			print(classPessoaInterfaces);
			/*
			 * Constructors
			 */
			System.out.println("Construtores Aluno:");
			Constructor[] classAlunoConstructors = classAluno.getConstructors();
			Arrays.asList(classAlunoConstructors).forEach(item -> {
				System.out.println(item);
			});
			/*
			 * Methods
			 */
			System.out.println("Métodos Aluno:");
			Method[] classAlunoMethods = classAluno.getMethods();
			print(classAlunoMethods);
			/*
			 * Fields
			 */
			// Campos publicos + campos da super classe
			System.out.println("Campos Aluno:");
			Field[] classAlunoFields = classAluno.getFields();
			print(classAlunoFields);

			// Campos declarados dentro da classe Aluno (independente do modificador)
			System.out.println("Campos declarados Aluno:");
			Field[] classAlunoDeclaredFields = classAluno.getDeclaredFields();
			print(classAlunoDeclaredFields);
			/*
			 * Obtaining Constructor Objects
			 */
			// Recupera um array com todos os construtores
			System.out.println("Construtores Aluno:");
			Constructor[] classAlunoConstructors2 = classAluno.getConstructors();
			print(classAlunoConstructors2);

			// Recupera um construtor espeficido pelo tipo de argumento.
			System.out.println("Construtor com parametro boolean Aluno:");
			Constructor constructorBoolean = classAluno.getConstructor(new Class[] { boolean.class });
			System.out.println(constructorBoolean);
			// Recupera os parametros dos construtores
			System.out.println("Parametros dos construtores");
			Class[] parameterTypesConstructorAluno = constructorBoolean.getParameterTypes();
			print(parameterTypesConstructorAluno);

			/*
			 * Instantiating Objects
			 */

			// Criando objeto com construtor padrao.
			Constructor constructorAluno = classAluno.getConstructor();
			Pessoa pessoa = (Pessoa) constructorAluno.newInstance();
			System.out.println(pessoa);

			// Criando objeto com construtoro com parametro.
			Constructor constructorBooleanAluno = classAluno.getConstructor(new Class[] { boolean.class });
			Aluno aluno = (Aluno) constructorBooleanAluno.newInstance(true);
			System.out.println(aluno.isAprovado());

			/*
			 * Obtaining Field
			 */
			System.out.println("Campos da classe Aluno");
			Field[] fieldsAluno = classAluno.getFields();
			Arrays.asList(fieldsAluno).forEach(item -> {
				System.out.println(item.getName());
				System.out.println(item);
			});

			/*
			 * Getting and Setting Field Values
			 */
			System.out.println("Get/Set valores");
			// Recupera construtor e cria objeto.
			Constructor constructorAluno2 = classAluno.getConstructor();
			Object objectAluno = constructorAluno2.newInstance();
			// Recupera o campo pelo nome
			Field fieldNome = classAluno.getField("nome");
			// Recupera o valor do campo
			Object valueFieldNome = fieldNome.get(objectAluno);
			System.out.println(valueFieldNome);
			// Insere valor no campo
			fieldNome.set(objectAluno, "Diogo");
			System.out.println(fieldNome.get(objectAluno));

			/*
			 * Obtaining Methods
			 */
			System.out.println("Recuperando métodos da classe Aluno");
			// Recuperando todos os metodos, inclusive da super classe
			Method[] methods = classAluno.getMethods();
			print(methods);
			// Recupera objeto
			Constructor constructorAluno3 = classAluno.getConstructor();
			Object objectAluno3 = constructorAluno2.newInstance();
			System.out.println("Executando método assinarContrato");
			// Recupera metodo
			Method assinarContratoMethod = classAluno.getMethod("assinarContrato");
			// Invoca metodo
			assinarContratoMethod.invoke(objectAluno3);

			System.out.println("Executando método mostrarNotas");
			// Recupera metodo
			Method mostrarNotasMethod = classAluno.getMethod("mostrarNotas", new Class[] { String.class });
			// Invoca metodo
			mostrarNotasMethod.invoke(objectAluno3, "1");

			/*
			 * Getters and Setters
			 */
			System.out.println("Imprimindo Getter e Setter");
			printGettersSetters(classAluno);
			/*
			 * Private Fields and Methods
			 */
			// Acessando campos privados (.setAccessible(true))
			Constructor constructorAluno4 = classAluno.getConstructor();
			Object objectAluno4 = constructorAluno2.newInstance();
			Field fieldAprovado = classAluno.getDeclaredField("aprovado");
			// Erro sem essa linha
			fieldAprovado.setAccessible(true);
			boolean isAprovado = (boolean) fieldAprovado.get(objectAluno4);
			System.out.println(isAprovado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void print(Object[] list) {
		Arrays.asList(list).forEach(item -> {
			System.out.println(item);
		});
	}

	public static void printGettersSetters(Class aClass) {
		Method[] methods = aClass.getMethods();

		for (Method method : methods) {
			if (isGetter(method))
				System.out.println("getter: " + method);
			if (isSetter(method))
				System.out.println("setter: " + method);
		}
	}

	public static boolean isGetter(Method method) {
		if (!method.getName().startsWith("get") && !method.getName().startsWith("is"))
			return false;
		if (method.getParameterTypes().length != 0)
			return false;
		if (void.class.equals(method.getReturnType()))
			return false;
		return true;
	}

	public static boolean isSetter(Method method) {
		if (!method.getName().startsWith("set"))
			return false;
		if (method.getParameterTypes().length != 1)
			return false;
		return true;
	}
}
