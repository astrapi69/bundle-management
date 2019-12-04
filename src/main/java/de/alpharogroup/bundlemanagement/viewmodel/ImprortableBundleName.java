package de.alpharogroup.bundlemanagement.viewmodel;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Locale;
import java.util.Properties;

@Data
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
