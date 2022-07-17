package diagrams;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.AbstractFilter;

public class Utils {
	public static void makeStaticCallGraphTrace(String inputResourcePath) {
		Launcher l = new Launcher();
		l.getEnvironment().setNoClasspath(true);
		l.addInputResource(inputResourcePath);


		l.getEnvironment().setNoClasspath(true);

		l.buildModel();
		CtModel model = l.getModel();
		Factory factory = l.getFactory();
		model.getAllTypes().stream().filter(el -> el instanceof CtClass).forEach(type -> {
			int i = 0;

			if (type.isInterface() || type.isAnnotationType())
				return;

			CtClass clazz = (CtClass) type;
			System.out.println("------------------- Class name:" + clazz.getSimpleName());
			List<CtField> fields = clazz.getFields();
			fields.stream().forEach(field -> System.out
					.println("field:" + field.getSimpleName() + ", type:" + field.getType().getSimpleName()));
			Set<CtMethod> methodSet = clazz.getAllMethods();
			for (CtMethod method : methodSet) {
				final List<CtInvocation<?>> invocations = method.getElements(new AbstractFilter<CtInvocation<?>>() {
					@Override
					public boolean matches(CtInvocation<?> element) {
						return super.matches(element);
					}
				});
				for (Iterator<CtInvocation<?>> invocationsIterator = invocations.iterator(); invocationsIterator
						.hasNext();) {
					CtInvocation invocation = invocationsIterator.next();
					CtExecutableReference executable = invocation.getExecutable();
					CtTypeReference declaringType = executable.getDeclaringType();
					CtType dkTypeDecl = declaringType.getDeclaration();
					Optional<CtField> findFirst = fields.stream()
							.filter(f -> f.getSimpleName().equals(declaringType.getSimpleName())).findFirst();

					System.out.println(method.getSignature() + " => "
							+ (dkTypeDecl != null ? dkTypeDecl.getSimpleName()
									: findFirst.isPresent() ? findFirst.get().getType().getSimpleName() : "")
							+ "" + "." + executable.toString());
					i++;
				}
			}

		});
	}
}
