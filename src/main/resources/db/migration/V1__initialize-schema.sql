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

create index idx_basenames_name
    on basenames (name);

create table countries
(
    id             integer not null
        constraint countries_pkey
            primary key,
    name           text
        constraint uc_countries_name
            unique,
    iso3166_a2name varchar(2)
        constraint uc_countries_iso3166_a2name
            unique
);

create index idx_countries_name
    on countries (name);

create index idx_countries_iso3166_a2name
    on countries (iso3166_a2name);

create table language_locales
(
    id      integer not null
        constraint language_locales_pkey
            primary key,
    version integer,
    locale  varchar(64)
        constraint uc_language_locales_locale
            unique
);

create table bundle_applications
(
    id                integer not null
        constraint bundle_applications_pkey
            primary key,
    name              text
        constraint idx_bundle_applications_name
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

create index idx_bundlenames_base_name_id
    on bundlenames (base_name_id);

create index idx_bundlenames_filepath
    on bundlenames (filepath);

create index idx_bundlenames_locale_id
    on bundlenames (locale_id);

create index idx_bundlenames_owner_id
    on bundlenames (owner_id);

create index idx_language_locales_locale
    on language_locales (locale);

create table languages
(
    id       integer not null
        constraint languages_pkey
            primary key,
    name     text
        constraint uc_languages_name
            unique,
    version  integer,
    iso639_1 varchar(2)
        constraint uc_languages_iso639_1
            unique
);

create index idx_languages_name
    on languages (name);

create index idx_languages_iso639_1
    on languages (iso639_1);

create table properties_keys
(
    id      integer not null
        constraint properties_keys_pkey
            primary key,
    name    text,
    version integer
);

create index idx_properties_keys_name
    on properties_keys (name);

create table properties_values
(
    id      integer not null
        constraint properties_values_pkey
            primary key,
    name    text,
    version integer
);

create index idx_properties_values_name
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

create index idx_resourcebundles_bundlename_id
    on resourcebundles (bundlename_id);

create index idx_resourcebundles_properties_key_id
    on resourcebundles (properties_key_id);

create index idx_resourcebundles_properties_value_id
    on resourcebundles (properties_value_id);
