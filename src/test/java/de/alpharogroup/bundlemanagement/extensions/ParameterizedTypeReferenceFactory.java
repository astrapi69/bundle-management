package de.alpharogroup.bundlemanagement.extensions;

import lombok.experimental.UtilityClass;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * A factory for creating ParameterizedTypeReference objects
 * @deprecated use instead the same name class from the next release from spring-tools-extesions.
 * <br>Note: will be removed when the next release from spring-tools-extesions will be released.
 */
@Deprecated
@UtilityClass
public class ParameterizedTypeReferenceFactory
{

	public static <K, V> ParameterizedTypeReference<Map<K, V>> newMapParameterizedTypeReference(Class<K> keyType, Class<V> valueType){

		return new ParameterizedTypeReference<Map<K, V>>() {
			@Override
			public Type getType() {
				Type type = super.getType();
				if (type instanceof ParameterizedType) {
					Type[] responseWrapperActualTypes = { keyType, valueType };
					ParameterizedType responseWrapperType = TypeUtils.parameterize(Map.class, responseWrapperActualTypes);
					return responseWrapperType;
				}
				return type;
			}
		};
	}

	public static <T> ParameterizedTypeReference<Set<T>> newSetParameterizedTypeReference(Class<T> entityClass){
		return new ParameterizedTypeReference<Set<T>>() {
			@Override
			public Type getType() {
				Type type = super.getType();
				if (type instanceof ParameterizedType) {
					Type[] responseWrapperActualTypes = { entityClass };
					ParameterizedType responseWrapperType = TypeUtils.parameterize(Set.class, responseWrapperActualTypes);
					return responseWrapperType;
				}
				return type;
			}
		};
	}

	public static <T> ParameterizedTypeReference<List<T>> newListParameterizedTypeReference(Class<T> entityClass){
		return new ParameterizedTypeReference<List<T>>() {
			@Override
			public Type getType() {
				Type type = super.getType();
				if (type instanceof ParameterizedType) {
					Type[] responseWrapperActualTypes = { entityClass };
					ParameterizedType responseWrapperType = TypeUtils.parameterize(List.class, responseWrapperActualTypes);
					return responseWrapperType;
				}
				return type;
			}
		};
	}

}
