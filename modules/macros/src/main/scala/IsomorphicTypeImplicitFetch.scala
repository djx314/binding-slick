package org.scalax.binding.slick

import slick.ast.BaseTypedType
import slick.jdbc.JdbcType
import slick.lifted.Isomorphism
import scala.language.experimental.macros

import scala.reflect.ClassTag

trait IsomorphicTypeImplicitFetch {
  implicit def isomorphicTypeBinding[A, B](implicit iso: Isomorphism[A, B], ct: ClassTag[A], jt: JdbcType[B] with BaseTypedType[B]): JdbcType[A] with BaseTypedType[A]
}

/*object IsomorphicTypeImplicitFetch {
  implicit def isomorphicTypeImplicitFetchImplicit: IsomorphicTypeImplicitFetch = macro IsomorphicTypeImplicitFetchMacroApply.macroApply

  class IsomorphicTypeImplicitFetchMacroApply(val c: scala.reflect.macros.blackbox.Context) {
    import c.universe._
    def macroApply: c.Expr[IsomorphicTypeImplicitFetch] = {
      c.Expr[IsomorphicTypeImplicitFetch] {
        q"""new _root_.org.scalax.binding.slick.IsomorphicTypeImplicitFetch {
            def isomorphicTypeImplicit[A, B](
              implicit iso: _root_.slick.lifted.Isomorphism[A, B],
              ct: _root_.scala.reflect.ClassTag[A],
              jt: _root_.slick.jdbc.JdbcType[B] with _root_.slick.ast.BaseTypedType[B]
            ): _root_.slick.jdbc.JdbcType[A] with _root_.slick.ast.BaseTypedType[A] = implicitly
          }"""
      }
    }
  }
}*/
