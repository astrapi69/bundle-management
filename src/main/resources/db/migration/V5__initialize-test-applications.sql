INSERT INTO bundle_applications (id, version, name, default_locale_id) VALUES (1, 1, 'test-bundle-application', 38);
INSERT INTO bundle_applications (id, version, name, default_locale_id) VALUES (2, 1, 'foo-bar.com', 29);

alter sequence seq_gen_bundle_applications restart with 3;

INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES (1, 29);
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES (1, 32);
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES (1, 38);
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES (1, 41);
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES (1, 48);
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES (2, 29);
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES (2, 38);

INSERT INTO basenames (id, version, name) VALUES (1,1,'test-resource-bundles');
INSERT INTO basenames (id, version, name) VALUES (2,1,'test');
INSERT INTO basenames (id, version, name) VALUES (3,1,'foo');

alter sequence seq_gen_basenames restart with 4;

INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES (1, 1, '/src/test/resources/messages', 1, 29, 1);
INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES (2, 1, '/src/test/resources/messages', 1, 41, 1);
INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES (3, 1, '/src/test/resources/errors', 2, 32, 1);
INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES (4, 1, '/src/test/resources/errors', 2, 48, 1);
INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES (5, 1, '/src/test/resources/strings', 3, 29, 2);
INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES (6, 1, '/src/test/resources/strings', 3, 38, 2);

alter sequence seq_gen_bundlenames restart with 7;

INSERT INTO properties_keys (id, version, name) VALUES (1, 1, 'resource.bundles.test.label');
INSERT INTO properties_keys (id, version, name) VALUES (2, 1, 'com.example.gui.window.buttons.save');
INSERT INTO properties_keys (id, version, name) VALUES (3, 1, 'com.example.gui.window.title');
INSERT INTO properties_keys (id, version, name) VALUES (4, 1, 'com.example.gui.prop.with.params.label');
INSERT INTO properties_keys (id, version, name) VALUES (5, 1, 'com.example.gui.window.buttons.cancel');
INSERT INTO properties_keys (id, version, name) VALUES (6, 1, 'com.foo-bar.gui.window.buttons.save');
INSERT INTO properties_keys (id, version, name) VALUES (7, 1, 'com.foo-bar.gui.window.title');
INSERT INTO properties_keys (id, version, name) VALUES (8, 1, 'com.foo-bar.gui.window.buttons.cancel');
INSERT INTO properties_keys (id, version, name) VALUES (9, 1, 'com.foo-bar.gui.window.buttons.choose');
INSERT INTO properties_keys (id, version, name) VALUES (10, 1, 'com.foo-bar.gui.window.buttons.back');

alter sequence seq_gen_properties_keys restart with 11;

INSERT INTO properties_values (id, version, name) VALUES (1, 1, 'Erstes label');
INSERT INTO properties_values (id, version, name) VALUES (2, 1, 'First label');
INSERT INTO properties_values (id, version, name) VALUES (3, 1, 'Speichern');
INSERT INTO properties_values (id, version, name) VALUES (4, 1, 'Hallo, dort!');
INSERT INTO properties_values (id, version, name) VALUES (5, 1, 'Hallo ich bin {0} und komme aus {1}.');
INSERT INTO properties_values (id, version, name) VALUES (6, 1, 'Abbrechen');
INSERT INTO properties_values (id, version, name) VALUES (7, 1, 'Save');
INSERT INTO properties_values (id, version, name) VALUES (8, 1, 'Hello, there!');
INSERT INTO properties_values (id, version, name) VALUES (9, 1, 'Hello i am {0} and i come from {1}.');
INSERT INTO properties_values (id, version, name) VALUES (10, 1, 'Cancel');
INSERT INTO properties_values (id, version, name) VALUES (11, 1, 'Sichern');
INSERT INTO properties_values (id, version, name) VALUES (12, 1, 'Eingang');
INSERT INTO properties_values (id, version, name) VALUES (13, 1, 'Annulieren');
INSERT INTO properties_values (id, version, name) VALUES (14, 1, 'Auswahl');
INSERT INTO properties_values (id, version, name) VALUES (15, 1, 'Hinten');
INSERT INTO properties_values (id, version, name) VALUES (16, 1, 'Persist');
INSERT INTO properties_values (id, version, name) VALUES (17, 1, 'Entrace');
INSERT INTO properties_values (id, version, name) VALUES (18, 1, 'Revoke');
INSERT INTO properties_values (id, version, name) VALUES (19, 1, 'Choose');
INSERT INTO properties_values (id, version, name) VALUES (20, 1, 'Back');

alter sequence seq_gen_properties_values restart with 21;

INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (1, 1, 1, 1, 1);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (2, 1, 1, 2, 2);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (3, 1, 2, 3, 3);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (4, 1, 3, 4, 3);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (5, 1, 4, 5, 3);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (6, 1, 5, 6, 3);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (7, 1, 2, 7, 4);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (8, 1, 3, 8, 4);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (9, 1, 4, 9, 4);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (10, 1, 5, 10, 4);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (11, 1, 6, 11, 5);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (12, 1, 7, 12, 5);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (13, 1, 8, 13, 5);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (14, 1, 9, 14, 5);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (15, 1, 10, 15, 5);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (16, 1, 6, 16, 6);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (17, 1, 7, 17, 6);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (18, 1, 8, 18, 6);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (19, 1, 9, 19, 6);
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES (20, 1, 10, 20, 6);

alter sequence seq_gen_resourcebundles restart with 21;