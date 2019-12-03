INSERT INTO bundle_applications (id, version, name, default_locale_id) VALUES ('0084d910-d153-4bd4-86bf-f5e5a8492c7e', 1, 'test-bundle-application','f910d316-add9-463f-8bc7-e4281c5c44f1');
INSERT INTO bundle_applications (id, version, name, default_locale_id) VALUES ('01ae3f21-2bc5-4889-8a34-1b14e18be24d', 1, 'foo-bar.com','6afa59eb-1c2b-4767-9ce8-166b405369a4');


INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES ('0084d910-d153-4bd4-86bf-f5e5a8492c7e','6afa59eb-1c2b-4767-9ce8-166b405369a4');
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES ('0084d910-d153-4bd4-86bf-f5e5a8492c7e','0f178aab-52f9-4459-83a9-c3078ca9d4d9');
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES ('0084d910-d153-4bd4-86bf-f5e5a8492c7e','f910d316-add9-463f-8bc7-e4281c5c44f1');
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES ('0084d910-d153-4bd4-86bf-f5e5a8492c7e','c513ed08-e65f-4352-b789-a8bbc5d2f1db');
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES ('0084d910-d153-4bd4-86bf-f5e5a8492c7e','c513ed08-e65f-4352-b789-a8bbc5d2f1db');
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES ('01ae3f21-2bc5-4889-8a34-1b14e18be24d','6afa59eb-1c2b-4767-9ce8-166b405369a4');
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES ('01ae3f21-2bc5-4889-8a34-1b14e18be24d','f910d316-add9-463f-8bc7-e4281c5c44f1');

INSERT INTO basenames (id, version, name) VALUES (1,1,'test-resource-bundles');
INSERT INTO basenames (id, version, name) VALUES (2,1,'test');
INSERT INTO basenames (id, version, name) VALUES (3,1,'foo');

INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES (1, 1, '/src/test/resources/messages', 1,'6afa59eb-1c2b-4767-9ce8-166b405369a4', 1);
INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES (2, 1, '/src/test/resources/messages', 1,'c513ed08-e65f-4352-b789-a8bbc5d2f1db', 1);
INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES (3, 1, '/src/test/resources/errors', 2,'0f178aab-52f9-4459-83a9-c3078ca9d4d9', 1);
INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES (4, 1, '/src/test/resources/errors', 2,'c513ed08-e65f-4352-b789-a8bbc5d2f1db', 1);
INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES (5, 1, '/src/test/resources/strings', 3,'6afa59eb-1c2b-4767-9ce8-166b405369a4', 2);
INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES (6, 1, '/src/test/resources/strings', 3,'f910d316-add9-463f-8bc7-e4281c5c44f1', 2);

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
