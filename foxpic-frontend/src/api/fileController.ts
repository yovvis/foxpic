// @ts-ignore
/* eslint-disable */
import request from '@/request'

/** delete PUT /api/file/delete */
export async function deleteUsingPut(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteUsingPUTParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/file/delete', {
    method: 'PUT',
    params: {
      ...params,
    },
    ...(options || {}),
  })
}

/** download GET /api/file/download */
export async function downloadUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.downloadUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<any>('/api/file/download', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  })
}

/** getHttpUrl GET /api/file/getHttpUrl */
export async function getHttpUrlUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getHttpUrlUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseString_>('/api/file/getHttpUrl', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  })
}

/** listObjs GET /api/file/listObjs */
export async function listObjsUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseListString_>('/api/file/listObjs', {
    method: 'GET',
    ...(options || {}),
  })
}

/** uploadFile POST /api/file/upload */
export async function uploadFileUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.uploadFileUsingPOSTParams,
  body: {},
  file?: File,
  options?: { [key: string]: any },
) {
  const formData = new FormData()

  if (file) {
    formData.append('file', file)
  }

  Object.keys(body).forEach((ele) => {
    const item = (body as any)[ele]

    if (item !== undefined && item !== null) {
      if (typeof item === 'object' && !(item instanceof File)) {
        if (item instanceof Array) {
          item.forEach((f) => formData.append(ele, f || ''))
        } else {
          formData.append(ele, JSON.stringify(item))
        }
      } else {
        formData.append(ele, item)
      }
    }
  })

  return request<API.BaseResponseString_>('/api/file/upload', {
    method: 'POST',
    params: {
      ...params,
    },
    data: formData,
    requestType: 'form',
    ...(options || {}),
  })
}
