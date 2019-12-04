INSERT INTO bundle_applications (id, version, name, default_locale_id) VALUES ('0084d910-d153-4bd4-86bf-f5e5a8492c7e', 1, 'test-bundle-application','f910d316-add9-463f-8bc7-e4281c5c44f1');
INSERT INTO bundle_applications (id, version, name, default_locale_id) VALUES ('01ae3f21-2bc5-4889-8a34-1b14e18be24d', 1, 'foo-bar.com','6afa59eb-1c2b-4767-9ce8-166b405369a4');


INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES ('0084d910-d153-4bd4-86bf-f5e5a8492c7e','6afa59eb-1c2b-4767-9ce8-166b405369a4');
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES ('0084d910-d153-4bd4-86bf-f5e5a8492c7e','0f178aab-52f9-4459-83a9-c3078ca9d4d9');
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES ('0084d910-d153-4bd4-86bf-f5e5a8492c7e','f910d316-add9-463f-8bc7-e4281c5c44f1');
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES ('0084d910-d153-4bd4-86bf-f5e5a8492c7e','c513ed08-e65f-4352-b789-a8bbc5d2f1db');
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES ('0084d910-d153-4bd4-86bf-f5e5a8492c7e','286dbfda-9035-4eda-a6b7-76b0dbe7697b');
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES ('01ae3f21-2bc5-4889-8a34-1b14e18be24d','6afa59eb-1c2b-4767-9ce8-166b405369a4');
INSERT INTO bundle_application_language_locales (application_id, language_locales_id) VALUES ('01ae3f21-2bc5-4889-8a34-1b14e18be24d','f910d316-add9-463f-8bc7-e4281c5c44f1');

INSERT INTO basenames (id, version, name) VALUES ('85a25b9b-cd19-45a3-a9d3-2f1b07231b72',1,'test-resource-bundles');
INSERT INTO basenames (id, version, name) VALUES ('711af8a1-466a-4393-9b4a-e83bff605330',1,'test');
INSERT INTO basenames (id, version, name) VALUES ('ff8a4a73-f0b9-4abf-a9bd-39573dd1968e',1,'foo');

INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES ('4cd700d8-a877-4f6c-8375-c892c960b08a', 1, '/src/test/resources/messages', '85a25b9b-cd19-45a3-a9d3-2f1b07231b72','6afa59eb-1c2b-4767-9ce8-166b405369a4', '0084d910-d153-4bd4-86bf-f5e5a8492c7e');
INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES ('fee54559-8da2-44df-898d-b058196879cb', 1, '/src/test/resources/messages', '85a25b9b-cd19-45a3-a9d3-2f1b07231b72','c513ed08-e65f-4352-b789-a8bbc5d2f1db', '0084d910-d153-4bd4-86bf-f5e5a8492c7e');
INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES ('10bca0b4-c4c2-4294-b8a4-d63d2ddb69b5', 1, '/src/test/resources/errors', '711af8a1-466a-4393-9b4a-e83bff605330','0f178aab-52f9-4459-83a9-c3078ca9d4d9', '0084d910-d153-4bd4-86bf-f5e5a8492c7e');
INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES ('8024acb3-362b-4cbd-a3b9-1cd4cfd88974', 1, '/src/test/resources/errors', '711af8a1-466a-4393-9b4a-e83bff605330','c513ed08-e65f-4352-b789-a8bbc5d2f1db', '0084d910-d153-4bd4-86bf-f5e5a8492c7e');
INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES ('291d6d01-d024-42d1-9fe1-410de16ba202', 1, '/src/test/resources/strings', 'ff8a4a73-f0b9-4abf-a9bd-39573dd1968e','6afa59eb-1c2b-4767-9ce8-166b405369a4', '01ae3f21-2bc5-4889-8a34-1b14e18be24d');
INSERT INTO bundlenames (id, version, filepath, base_name_id, locale_id, owner_id) VALUES ('f8f85645-2e8c-4de1-bd23-791745200690', 1, '/src/test/resources/strings', 'ff8a4a73-f0b9-4abf-a9bd-39573dd1968e','f910d316-add9-463f-8bc7-e4281c5c44f1', '01ae3f21-2bc5-4889-8a34-1b14e18be24d');

INSERT INTO properties_keys (id, version, name) VALUES ('393aede7-31eb-410a-b1b3-8b1bf2f058c8', 1, 'resource.bundles.test.label'); -- 1
INSERT INTO properties_keys (id, version, name) VALUES ('f3c68bdd-3964-4ffa-b3c7-c8810ad6408e', 1, 'com.example.gui.window.buttons.save'); -- 2
INSERT INTO properties_keys (id, version, name) VALUES ('62aa3f43-b9a2-4416-bdb5-ebf8b492cf2d', 1, 'com.example.gui.window.title'); -- 3
INSERT INTO properties_keys (id, version, name) VALUES ('40f8c592-79be-4dbd-9b5f-d3c38df8535a', 1, 'com.example.gui.prop.with.params.label'); -- 4
INSERT INTO properties_keys (id, version, name) VALUES ('e87db24b-9900-43c9-a320-91b0046fafb9', 1, 'com.example.gui.window.buttons.cancel'); -- 5
INSERT INTO properties_keys (id, version, name) VALUES ('e2ca49a3-644e-4724-936b-4f4edb3b994b', 1, 'com.foo-bar.gui.window.buttons.save'); -- 6
INSERT INTO properties_keys (id, version, name) VALUES ('daa52e13-695e-4c0a-ae05-98985e8d9f45', 1, 'com.foo-bar.gui.window.title'); -- 7
INSERT INTO properties_keys (id, version, name) VALUES ('10a3ea18-703d-47bc-9878-b34b901243b4', 1, 'com.foo-bar.gui.window.buttons.cancel'); -- 8
INSERT INTO properties_keys (id, version, name) VALUES ('63cc1be0-1b4b-4e43-a9aa-f09c064cf6f4', 1, 'com.foo-bar.gui.window.buttons.choose'); -- 9
INSERT INTO properties_keys (id, version, name) VALUES ('f39586f5-a8d9-4f21-861b-554efb593ec8', 1, 'com.foo-bar.gui.window.buttons.back'); -- 10

INSERT INTO properties_values (id, version, name) VALUES ('4cd700d8-a877-4f6c-8375-c892c960b08a', 1, 'Erstes label'); -- 1
INSERT INTO properties_values (id, version, name) VALUES ('fee54559-8da2-44df-898d-b058196879cb', 1, 'First label'); -- 2
INSERT INTO properties_values (id, version, name) VALUES ('10bca0b4-c4c2-4294-b8a4-d63d2ddb69b5', 1, 'Speichern'); -- 3
INSERT INTO properties_values (id, version, name) VALUES ('8024acb3-362b-4cbd-a3b9-1cd4cfd88974', 1, 'Hallo, dort!'); -- 4
INSERT INTO properties_values (id, version, name) VALUES ('291d6d01-d024-42d1-9fe1-410de16ba202', 1, 'Hallo ich bin {0} und komme aus {1}.'); -- 5
INSERT INTO properties_values (id, version, name) VALUES ('f8f85645-2e8c-4de1-bd23-791745200690', 1, 'Abbrechen'); -- 6
INSERT INTO properties_values (id, version, name) VALUES ('b310a966-c915-4b54-aa2d-6654ebd76094', 1, 'Save'); -- 7
INSERT INTO properties_values (id, version, name) VALUES ('b0b87aeb-bd39-4cdd-9a57-99649672d8ef', 1, 'Hello, there!'); -- 8
INSERT INTO properties_values (id, version, name) VALUES ('88f774e9-e912-4d2c-936e-7b737f45eca9', 1, 'Hello i am {0} and i come from {1}.'); -- 9
INSERT INTO properties_values (id, version, name) VALUES ('3cdb76bd-6421-4674-ad52-3b68a92fcb9c', 1, 'Cancel'); -- 10
INSERT INTO properties_values (id, version, name) VALUES ('ea2cb365-80bf-4d78-bfd5-b77fb6495fc1', 1, 'Sichern'); -- 11
INSERT INTO properties_values (id, version, name) VALUES ('c8f3fa39-56c3-42a1-827a-f69dfe03087f', 1, 'Eingang'); -- 12
INSERT INTO properties_values (id, version, name) VALUES ('81a679a7-a18c-4dac-8245-840f61d181aa', 1, 'Annulieren'); -- 13
INSERT INTO properties_values (id, version, name) VALUES ('c680668b-6ae7-4b61-a541-7283013c6c73', 1, 'Auswahl'); -- 14
INSERT INTO properties_values (id, version, name) VALUES ('9ebbec1a-a23a-47ea-b42f-50002c399b4f', 1, 'Hinten'); -- 15
INSERT INTO properties_values (id, version, name) VALUES ('2804fb21-b49b-4e90-a6dd-b539908817eb', 1, 'Persist'); -- 16
INSERT INTO properties_values (id, version, name) VALUES ('7a4a687e-9004-4377-b013-ab8ee8521b65', 1, 'Entrace'); -- 17
INSERT INTO properties_values (id, version, name) VALUES ('1da6d25b-517f-4d57-b07d-e00d991f210e', 1, 'Revoke'); -- 18
INSERT INTO properties_values (id, version, name) VALUES ('d83af033-8aee-4a27-942a-1f9f698fcbe2', 1, 'Choose'); -- 19
INSERT INTO properties_values (id, version, name) VALUES ('a2cf4d01-fa37-4339-87e4-2fa69a390a2b', 1, 'Back'); -- 20

INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('323823e5-c1c7-4a95-a2d3-db21223cfc02', 1, '393aede7-31eb-410a-b1b3-8b1bf2f058c8', '4cd700d8-a877-4f6c-8375-c892c960b08a', '4cd700d8-a877-4f6c-8375-c892c960b08a');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('06c156e0-fc81-479d-8624-77bc814a52f7', 1, '393aede7-31eb-410a-b1b3-8b1bf2f058c8', 'fee54559-8da2-44df-898d-b058196879cb', 'fee54559-8da2-44df-898d-b058196879cb');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('3a3d96f1-8092-4897-b471-4437a37ecb81', 1, 'f3c68bdd-3964-4ffa-b3c7-c8810ad6408e', '10bca0b4-c4c2-4294-b8a4-d63d2ddb69b5', '10bca0b4-c4c2-4294-b8a4-d63d2ddb69b5');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('379a005f-bc56-4e78-a9f2-1bb1755c34b0', 1, '62aa3f43-b9a2-4416-bdb5-ebf8b492cf2d', '8024acb3-362b-4cbd-a3b9-1cd4cfd88974', '10bca0b4-c4c2-4294-b8a4-d63d2ddb69b5');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('05ab8ff4-2786-4d5a-8008-f5b8abf6fadc', 1, '40f8c592-79be-4dbd-9b5f-d3c38df8535a', '291d6d01-d024-42d1-9fe1-410de16ba202', '10bca0b4-c4c2-4294-b8a4-d63d2ddb69b5');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('9d8ec67e-a49a-4253-b1af-51110d95c4c4', 1, 'e87db24b-9900-43c9-a320-91b0046fafb9', 'f8f85645-2e8c-4de1-bd23-791745200690', '10bca0b4-c4c2-4294-b8a4-d63d2ddb69b5');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('2ed64014-5e66-416e-a192-6532eb7d82fe', 1, 'f3c68bdd-3964-4ffa-b3c7-c8810ad6408e', 'b310a966-c915-4b54-aa2d-6654ebd76094','8024acb3-362b-4cbd-a3b9-1cd4cfd88974');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('fc16451e-6af1-41c5-9e48-e40d04f96f3d', 1, '62aa3f43-b9a2-4416-bdb5-ebf8b492cf2d', 'b0b87aeb-bd39-4cdd-9a57-99649672d8ef','8024acb3-362b-4cbd-a3b9-1cd4cfd88974');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('e3c95d1b-0266-46fe-8884-afaee2329eae', 1, '40f8c592-79be-4dbd-9b5f-d3c38df8535a', '88f774e9-e912-4d2c-936e-7b737f45eca9','8024acb3-362b-4cbd-a3b9-1cd4cfd88974');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('c9f5ba13-617b-4bbd-9ff8-308db7014f62', 1, 'e87db24b-9900-43c9-a320-91b0046fafb9', '3cdb76bd-6421-4674-ad52-3b68a92fcb9c','8024acb3-362b-4cbd-a3b9-1cd4cfd88974');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('47585b2e-b55d-4613-85d7-fb272722b676', 1, 'e2ca49a3-644e-4724-936b-4f4edb3b994b', 'ea2cb365-80bf-4d78-bfd5-b77fb6495fc1','291d6d01-d024-42d1-9fe1-410de16ba202');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('c1f12490-3835-48f5-b805-dbb4d0e4445c', 1, 'daa52e13-695e-4c0a-ae05-98985e8d9f45', 'c8f3fa39-56c3-42a1-827a-f69dfe03087f','291d6d01-d024-42d1-9fe1-410de16ba202');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('344362a9-1676-403f-96ef-9522c41ff375', 1, '10a3ea18-703d-47bc-9878-b34b901243b4', '81a679a7-a18c-4dac-8245-840f61d181aa','291d6d01-d024-42d1-9fe1-410de16ba202');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('1f8d8374-49be-4a01-867c-ce260c4020eb', 1, '63cc1be0-1b4b-4e43-a9aa-f09c064cf6f4', 'c680668b-6ae7-4b61-a541-7283013c6c73','291d6d01-d024-42d1-9fe1-410de16ba202');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('f8115d92-c523-4cca-92d1-174a62f2f7ac', 1, 'f39586f5-a8d9-4f21-861b-554efb593ec8', '9ebbec1a-a23a-47ea-b42f-50002c399b4f','291d6d01-d024-42d1-9fe1-410de16ba202');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('a28cd8ac-57ff-4672-a263-86465455ae15', 1, 'e2ca49a3-644e-4724-936b-4f4edb3b994b', '2804fb21-b49b-4e90-a6dd-b539908817eb','f8f85645-2e8c-4de1-bd23-791745200690');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('1e26c6ec-554b-4c75-9155-e514f54633b5', 1, 'daa52e13-695e-4c0a-ae05-98985e8d9f45', '7a4a687e-9004-4377-b013-ab8ee8521b65','f8f85645-2e8c-4de1-bd23-791745200690');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('483a1fce-ca57-45a9-b995-59d2e0ebdb42', 1, '10a3ea18-703d-47bc-9878-b34b901243b4', '1da6d25b-517f-4d57-b07d-e00d991f210e','f8f85645-2e8c-4de1-bd23-791745200690');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('6ed8318e-6f7c-4704-91f7-031136d8f3ba', 1, '63cc1be0-1b4b-4e43-a9aa-f09c064cf6f4', 'd83af033-8aee-4a27-942a-1f9f698fcbe2','f8f85645-2e8c-4de1-bd23-791745200690');
INSERT INTO resourcebundles (id, version, properties_key_id, properties_value_id, bundlename_id) VALUES ('47866600-7208-440b-bd2a-649380abdcc7', 1, 'f39586f5-a8d9-4f21-861b-554efb593ec8', 'a2cf4d01-fa37-4339-87e4-2fa69a390a2b','f8f85645-2e8c-4de1-bd23-791745200690');
