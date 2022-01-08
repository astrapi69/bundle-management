/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.bundlemanagement.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import io.github.astrapi69.bundlemanagement.jpa.entity.PropertiesKeyParts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface PropertiesKeyPartsRepository extends JpaRepository<PropertiesKeyParts, UUID>
{

	@Transactional
	@Query("select entity from PropertiesKeyParts entity where entity.depth=:depth " +
		" and entity.value=:value")
	List<PropertiesKeyParts> findByDepthAndValue(@Param("depth") int depth,
		@Param("value") String value);

	@Transactional
	@Query("select entity from PropertiesKeyParts entity where entity.depth=:depth " +
		" and entity.value=:value " +
		" and entity.parent=:parent")
	List<PropertiesKeyParts> findByDepthAndValueAndParent(@Param("depth") int depth,
		@Param("value") String value,
		@Param("parent") PropertiesKeyParts parent);

	@Transactional
	@Query("select entity from PropertiesKeyParts entity where entity.value=:value " +
		" and entity.parent is null")
	Optional<PropertiesKeyParts> findRootByValue(@Param("value") String value);

	List<PropertiesKeyParts> findByValue(String value);

	@Query(
		value = "WITH RECURSIVE ancestors(id, parent_id, value, level) AS ("
			+ "   SELECT pkp.id, pkp.parent_id, pkp.value, 1 AS level "
			+ "   FROM properties_key_parts pkp "
			+ "   WHERE pkp.id = :treeId "
			+ "   UNION ALL "
			+ "   SELECT parent.id, parent.parent_id, parent.value, child.level + 1 AS level "
			+ "   FROM properties_key_parts parent "
			+ "   JOIN ancestors child "
			+ "   ON parent.id = child.parent_id "
			+ " )"
			+ "SELECT value from ancestors ORDER BY level DESC"
		, nativeQuery = true)
	List<String> findAncestry(@Param("treeId") UUID treeId);
}
