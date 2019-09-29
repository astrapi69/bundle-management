create sequence seq_gen_basenames;

create sequence seq_gen_bundle_applications;

create sequence seq_gen_bundlenames;

create sequence seq_gen_countries;

create sequence seq_gen_language_locales;

create sequence seq_gen_languages;

create sequence seq_gen_properties_keys;

create sequence seq_gen_properties_values;

create sequence seq_gen_resourcebundles;

create table basenames
(
    id      integer not null
        constraint basenames_pkey
            primary key,
    name    text,
    version integer
);

create index idx_basenamesname
    on basenames (name);

create table countries
(
    id             integer not null
        constraint countries_pkey
            primary key,
    name           text
        constraint uk_1pyiwrqimi3hnl3vtgsypj5r
            unique,
    iso3166_a2name varchar(2)
        constraint uk_9xthkqhg18dqjgwtaorxs9nss
            unique
);

create index idx_countriesname
    on countries (name);

create index idx_countriesiso3166_a2name
    on countries (iso3166_a2name);

create table language_locales
(
    id      integer not null
        constraint language_locales_pkey
            primary key,
    version integer,
    locale  varchar(64)
        constraint uk_o6y96fuy6rh296t7hio2yktul
            unique
);

create table bundle_applications
(
    id                integer not null
        constraint bundle_applications_pkey
            primary key,
    name              text
        constraint uk_q403edmj0haflkgh40khuy4v
            unique,
    version           integer,
    default_locale_id integer
        constraint fk_bundle_applications_default_locale_id
            references language_locales
);

create table bundle_application_language_locales
(
    application_id      integer not null
        constraint fk_bundle_application_id
            references bundle_applications,
    language_locales_id integer not null
        constraint fk_bundle_application_language_locales_id
            references language_locales,
    constraint bundle_application_language_locales_pkey
        primary key (application_id, language_locales_id)
);

create table bundlenames
(
    id           integer not null
        constraint bundlenames_pkey
            primary key,
    version      integer,
    filepath     varchar(4096),
    base_name_id integer
        constraint fk_bundlenames_base_name_id
            references basenames,
    locale_id    integer
        constraint fk_bundlenames_locale_id
            references language_locales,
    owner_id     integer
        constraint fk_bundlenames_owner_id
            references bundle_applications
);

create index idx_bundlenamesbase_name_id
    on bundlenames (base_name_id);

create index idx_bundlenamesfilepath
    on bundlenames (filepath);

create index idx_bundlenameslocale_id
    on bundlenames (locale_id);

create index idx_bundlenamesowner_id
    on bundlenames (owner_id);

create index idx_language_localeslocale
    on language_locales (locale);

create table languages
(
    id       integer not null
        constraint languages_pkey
            primary key,
    name     text
        constraint uk_f6axmaokhmrbmm746866v0uyu
            unique,
    version  integer,
    iso639_1 varchar(2)
        constraint uk_c2kucnv72r38as03amypre8no
            unique
);

create index idx_languagesname
    on languages (name);

create index idx_languagesiso639_1
    on languages (iso639_1);

create table properties_keys
(
    id      integer not null
        constraint properties_keys_pkey
            primary key,
    name    text,
    version integer
);

create index idx_properties_keysname
    on properties_keys (name);

create table properties_values
(
    id      integer not null
        constraint properties_values_pkey
            primary key,
    name    text,
    version integer
);

create index idx_properties_valuesname
    on properties_values (name);

create table resourcebundles
(
    id                  integer not null
        constraint resourcebundles_pkey
            primary key,
    version             integer,
    bundlename_id       integer
        constraint fk_resourcebundles_bundlename_id
            references bundlenames,
    properties_key_id   integer
        constraint fk_resourcebundles_properties_key_id
            references properties_keys,
    properties_value_id integer
        constraint fk_resourcebundles_properties_value_id
            references properties_values
);

create index idx_resourcebundlesbundlename_id
    on resourcebundles (bundlename_id);

create index idx_resourcebundlesproperties_key_id
    on resourcebundles (properties_key_id);

create index idx_resourcebundlesproperties_value_id
    on resourcebundles (properties_value_id);

