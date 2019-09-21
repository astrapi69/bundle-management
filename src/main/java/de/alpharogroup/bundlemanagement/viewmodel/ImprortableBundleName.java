package de.alpharogroup.bundlemanagement.viewmodel;

import java.util.Locale;
import java.util.Properties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImprortableBundleName
{
	String baseName;
	String bundleappname;
	String filepath;
	Locale locale;
	Properties properties;
}